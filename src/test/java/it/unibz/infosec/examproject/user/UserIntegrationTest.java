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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    public void itFindsAUserAfterItsCreation () {

        User inputUser = new User("email@exmaple.com", "pw", "salt", new BigInteger(String.valueOf(123)), new BigInteger(String.valueOf(456)), new BigInteger(String.valueOf(789)) , 0, 1);

        User created = restTemplate.postForObject(baseUrl + "/create", inputUser, User.class);
        User retrieved = restTemplate.getForObject(baseUrl + "/" + created.getId(), User.class);
        String userPw = Hashing.getDigest(inputUser.getPassword() + retrieved.getSalt());

        Assertions.assertAll(
                () -> assertThat(retrieved.getId()).isNotNull(),
                () -> assertThat(retrieved.getEmail()).isEqualTo(inputUser.getEmail()),
                () -> assertThat(retrieved.getPassword()).isEqualTo(userPw),
                () -> assertThat(retrieved.getSalt()).isNotNull(),
                () -> assertThat(retrieved.getPublicKey()).isNotNull(),
                () -> assertThat(retrieved.getPrivateKey()).isNotNull(),
                //TODO: for some reasong the nkey of the user becomes null in the response obj, check unit tests
//                () -> assertThat(retrieved.getNKey()).isNotNull(),
                () -> assertThat(retrieved.getBalance()).isEqualTo(inputUser.getBalance()),
                () -> assertThat(retrieved.getType()).isEqualTo(inputUser.getType())
        );
    }

    @Test
    public void itDeletesAUserAfterCreation() {

        User inputUser = new User("email@exmaple.com", "pw", "salt", new BigInteger(String.valueOf(123)), new BigInteger(String.valueOf(456)), new BigInteger(String.valueOf(789)) , 0, 1);

        User created = restTemplate.postForObject(baseUrl + "/create", inputUser, User.class);

        restTemplate.delete(baseUrl + "/delete/" + created.getId());

        assertThatThrownBy(() -> restTemplate.getForObject(baseUrl + "/" + created.getId(), User.class));
    }

    @Test
    public void itCreatesSeveralUsersAndGetThemAll() {
        // given
        User inputUser1 = new User("email1@exmaple.com", "pw1", "salt", new BigInteger(String.valueOf(123)), new BigInteger(String.valueOf(456)), new BigInteger(String.valueOf(789)) , 0, 1);
        User inputUser2 = new User("email2@exmaple.com", "pw2", "salt", new BigInteger(String.valueOf(123)), new BigInteger(String.valueOf(456)), new BigInteger(String.valueOf(789)) , 0, 1);
        User inputUser3 = new User("email3@exmaple.com", "pw3", "salt", new BigInteger(String.valueOf(123)), new BigInteger(String.valueOf(456)), new BigInteger(String.valueOf(789)) , 0, 1);


        // when
        restTemplate.postForObject(baseUrl + "/create", inputUser1, User.class);
        restTemplate.postForObject(baseUrl + "/create", inputUser2, User.class);
        restTemplate.postForObject(baseUrl + "/create", inputUser3, User.class);

        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {
        };

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(baseUrl + "/getAll", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), typeRef);
        List<User> myModelClasses = responseEntity.getBody();


        // then

        assertThat(myModelClasses).isNotNull();

        User user1 = (User) myModelClasses.get(0);
        User user2 = (User) myModelClasses.get(1);
        User user3 = (User) myModelClasses.get(2);

        Assertions.assertAll(
                () -> assertThat(user1.getId()).isNotNull(),
                () -> assertThat(user1.getEmail()).isEqualTo(inputUser1.getEmail()),
                () -> assertThat(user2.getId()).isNotNull(),
                () -> assertThat(user2.getEmail()).isEqualTo(inputUser2.getEmail()),
                () -> assertThat(user3.getId()).isNotNull(),
                () -> assertThat(user3.getEmail()).isEqualTo(inputUser3.getEmail())
        );

    }

    @Test
    public void itThrowsErrorSearchingNonExistingUsers() {
        //db is empty
        assertThatThrownBy(() -> restTemplate.getForObject(baseUrl + "/50", User.class));
    }

}
