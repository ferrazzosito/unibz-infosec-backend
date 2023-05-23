package it.unibz.infosec.examproject.security;

import it.unibz.infosec.examproject.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SaltPasswordEncoder implements PasswordEncoder {

    @Autowired
    private UserRepository userRepository;

    public SaltPasswordEncoder() {
    }

    @Override
    public String encode(CharSequence rawPassword) {
        final String hopefullyUsername = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest().getAttribute("username").toString();
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
