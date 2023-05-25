package it.unibz.infosec.examproject.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "managed_user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int balance;

    private int type;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    private BigInteger publicKey;

    private BigInteger nKey;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigInteger privateKey;

    public User() {}
    public User(String email, String password, String salt, BigInteger privateKey, BigInteger publicKey, BigInteger nKey, int balance, int type) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.nKey = nKey;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.balance = balance;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public int addToBalance (int amount) {
        balance += amount;
        return getBalance();
    }
    public int getBalance() {
        return balance;
    }

    public int getType() {
        return type;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getNKey () {
        return nKey;
    }
}
