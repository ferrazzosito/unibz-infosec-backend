package it.unibz.infosec.examproject.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageUsers {

    private UserRepository userRepository;
    private SearchUsers searchUsers;

    @Autowired
    public ManageUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.searchUsers = searchUsers;
    }

    private User validateUser (Long id) {

        Optional<User> maybeUser = userRepository.findById(id);

        if(maybeUser.isEmpty())
            throw new IllegalArgumentException("User with id '" + id + "' does not exist yet!");

        return maybeUser.get();
    }

    public User createUser(String email, String password, int type) {

        //TODO: hash password, generate salt,...

        return userRepository.save(new User(email, password, "sds", 0, type));
    }

    public User readUser(Long id) {

        User user = validateUser(id);

        return user;

    }

    public User updateUser (Long id, int amountToAdd) {

        User user = validateUser(id);

        user.addToBalance(amountToAdd);

        return userRepository.save(user);
    }

    public User deleteUser (Long id) {

        User user = validateUser(id);

        userRepository.delete(user);

        return user;
    }

}