package it.unibz.infosec.examproject.security;

import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.crypto.RandomUtils;
import it.unibz.infosec.examproject.util.crypto.hashing.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.Iterator;

public class SaltPasswordEncoder implements PasswordEncoder {

    @Autowired
    private UserRepository userRepository;

    public SaltPasswordEncoder() {
    }

    @Override
    public String encode(CharSequence rawPassword) {
        final String maybeEmail = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest().getParameter("email");

//        while(maybeEmail.hasNext())
//            System.out.println(maybeEmail.next());


//        final String salt = RandomUtils.generateRandomSalt(32);
//        final String hashedPassword = Hashing.getDigest(rawPassword + salt);
//
        System.out.println(maybeEmail);

        return "a";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        final String maybeEmail = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest().getParameter("u1_0.email");

        System.out.println(maybeEmail);

        return true;
    }
}
