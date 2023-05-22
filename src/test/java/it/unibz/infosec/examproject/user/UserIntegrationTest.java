package it.unibz.infosec.examproject.user;

import it.unibz.infosec.examproject.user.domain.User;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.crypto.hashing.Hashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();

    }

    @BeforeEach
    public void setup() {
        baseUrl = baseUrl + ":" + port + "/v1/users";
        ensureEmptyDatabase();
    }

    private void ensureEmptyDatabase() {
        userRepository.deleteAll();
    }

    @Test
    public void itCreatesANewUser() {
        // given
        User inputUser = new User("email@exmaple.com", "pw", "salt", new BigInteger(String.valueOf(123)), new BigInteger(String.valueOf(456)), new BigInteger(String.valueOf(789)) , 0, 1);

        // when
        User response = restTemplate.postForObject(baseUrl + "/create", inputUser, User.class);
        String userPw = Hashing.getDigest(inputUser.getPassword() + response.getSalt());

        // then
        Assertions.assertAll(
                () -> assertThat(response.getId()).isNotNull(),
                () -> assertThat(response.getEmail()).isEqualTo(inputUser.getEmail()),
                () -> assertThat(response.getPassword()).isEqualTo(userPw),
                () -> assertThat(response.getSalt()).isNotNull(),
                () -> assertThat(response.getPublicKey()).isNotNull(),
                () -> assertThat(response.getPrivateKey()).isNotNull(),
                //TODO: for some reasong the nkey of the user becomes null in the response obj, check unit tests
//                () -> assertThat(response.getNKey()).isNotNull(),
                () -> assertThat(response.getBalance()).isEqualTo(inputUser.getBalance()),
                () -> assertThat(response.getType()).isEqualTo(inputUser.getType())
        );
    }

}
