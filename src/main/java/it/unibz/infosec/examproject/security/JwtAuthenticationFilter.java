package it.unibz.infosec.examproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtGenerator tokenGenerator;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request,
                                 @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String token = getJwtFromRequest(request);

        logger.info(token);
        if (StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
            final String email = tokenGenerator.getEmailFromJwt(token);
            final String csrf = tokenGenerator.getCsrfTokenFromJwt(token);
            if(request.getMethod().equalsIgnoreCase("post")) {
                boolean csrfFound = false;
                for(Cookie cookie : request.getCookies()) {
                    if("csrf".equals(cookie.getName()))
                        csrfFound = true;
                }
                if(!csrfFound) {
                    logger.info("dio");
                    throw new IllegalStateException("dio capra");
                }
            }
            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            final UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            userDetails.getPassword(), userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest (HttpServletRequest request)  {

        String cookie = Arrays.stream(Optional.ofNullable(request.getCookies())
                        .orElse(new Cookie[0]))
                .filter(c -> "jwt".equalsIgnoreCase(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
        return cookie;

    }

}
