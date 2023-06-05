package it.unibz.infosec.examproject.user.domain;

import it.unibz.infosec.examproject.util.crypto.RandomUtils;
import it.unibz.infosec.examproject.util.crypto.hashing.Hashing;
import it.unibz.infosec.examproject.util.crypto.rsa.RSA;
import it.unibz.infosec.examproject.util.crypto.rsa.RSAKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageUsers {

    private final UserRepository userRepository;
    private SearchUsers searchUsers;

    @Autowired
    public ManageUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserEntity validateUser(Long id) {
        final Optional<UserEntity> maybeUser = userRepository.findById(id);
        if (maybeUser.isEmpty())
            throw new IllegalArgumentException("User with id '" + id + "' does not exist yet!");
        return maybeUser.get();
    }

    public UserEntity createUser(String email, String password, Role role) {
        final String salt = RandomUtils.generateRandomSalt(32);
        final String hashedPassword = Hashing.getDigest(password + salt);

        final RSAKeyPair keyPair = RSA.generateKeys(1024);
        return userRepository.save(new UserEntity(email, hashedPassword, salt, keyPair.getPrivateExponent(), keyPair.getPublicExponent(), keyPair.getN(),0, role.getName()));
    }

    public UserEntity readUser(Long id) {
        return validateUser(id);
    }

    public UserEntity updateUser(Long id, Long updaterId, int amountToAdd) {
        final UserEntity userEntity = validateUser(id);
        if (!userEntity.getId().equals(updaterId)) {
            throw new IllegalArgumentException("Only the user itself can update its info");
        }
        userEntity.addToBalance(amountToAdd);
        return userRepository.save(userEntity);
    }

    public UserEntity deleteUser(Long id) {
        final UserEntity userEntity = validateUser(id);
        userRepository.delete(userEntity);
        return userEntity;
    }
}
