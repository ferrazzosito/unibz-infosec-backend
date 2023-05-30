package it.unibz.infosec.examproject.user.domain;

import it.unibz.infosec.examproject.util.crypto.RandomUtils;
import it.unibz.infosec.examproject.util.crypto.hashing.Hashing;
import it.unibz.infosec.examproject.util.crypto.rsa.RSA;
import it.unibz.infosec.examproject.util.crypto.rsa.RSAKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ManageUsers {

    private final UserRepository userRepository;
    private SearchUsers searchUsers;

    @Autowired
    public ManageUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserEntity validateUser (Long id) {

        Optional<UserEntity> maybeUser = userRepository.findById(id);

        if(maybeUser.isEmpty())
            throw new IllegalArgumentException("User with id '" + id + "' does not exist yet!");

        return maybeUser.get();
    }

    public UserEntity createUser(String email, String password, Role role) {

        final String salt = RandomUtils.generateRandomSalt(32);
        final String hashedPassword = Hashing.getDigest(password + salt);

        final RSAKeyPair keyPair = RSA.generateKeys();
        return userRepository.save(new UserEntity(email, hashedPassword, salt, keyPair.getPrivateExponent(), keyPair.getPublicExponent(), keyPair.getN(),0, role.getName()));
    }

    public UserEntity readUser(Long id) {

        UserEntity userEntity = validateUser(id);

        return userEntity;

    }

    public UserEntity updateUser (Long id, int amountToAdd) {

        UserEntity userEntity = validateUser(id);

        userEntity.addToBalance(amountToAdd);

        return userRepository.save(userEntity);
    }

    public UserEntity deleteUser (Long id) {

        UserEntity userEntity = validateUser(id);

        userRepository.delete(userEntity);

        return userEntity;
    }

}
