package it.unibz.infosec.examproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.util.crypto.RandomUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtGenerator {

    public String generateToken(final UserEntity user) {
        final String email = user.getEmail();
        final Date currentDate = new Date();
        final Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        final String csrfToken = RandomUtils.generateRandomSalt(32);
        //
        final HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        claims.put("csrf", csrfToken);

        //csrf token
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();
    }

    public String getEmailFromJwt(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getCsrfTokenFromJwt(String jwt) {
        final Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(jwt)
                .getBody();
        return claims.get("csrf", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SECRET)
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }

    }

}
