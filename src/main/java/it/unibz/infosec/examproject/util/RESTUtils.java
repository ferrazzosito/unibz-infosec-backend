package it.unibz.infosec.examproject.util;

import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class RESTUtils {

    public static UserEntity getLoggedUser(final UserRepository userRepository) {
        final User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(loggedUser.getUsername()).orElseThrow(() ->
                new IllegalStateException("Logged user is invalid. This should not happen."));
    }

}
