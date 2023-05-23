package it.unibz.infosec.examproject.security;

import it.unibz.infosec.examproject.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SaltPasswordEncoder implements PasswordEncoder {

    private UserRepository userRepository;

    @Autowired
    public SaltPasswordEncoder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
