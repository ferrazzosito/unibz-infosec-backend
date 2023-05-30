package it.unibz.infosec.examproject.security;

import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.crypto.hashing.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<UserEntity> maybeUser = userRepository.findByEmail(email);
        if (maybeUser.isPresent()) {
            Logger.getLogger("CustomAuthenticationProvider").log(Level.INFO, maybeUser.get().toString());
            UserEntity user = maybeUser.get();
            final String salt = user.getSalt();
            final String hashedPassword = Hashing.getDigest(password + salt);

            if(hashedPassword.equals(user.getPassword()))
                return new UsernamePasswordAuthenticationToken(
                        email, hashedPassword, List.of(new SimpleGrantedAuthority(
                                user.getRole().getName())));
        }

        return null;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
