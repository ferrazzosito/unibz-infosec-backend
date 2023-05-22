package it.unibz.infosec.examproject.user.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode
@Entity
@Table(name = "managed_user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String salt;

    private BigInteger publicKey;

    private BigInteger privateKey;

    private BigInteger nKey;

    private Integer balance;

    private Integer userRole;

    public User() {}
    public User(String email, String password, String salt, BigInteger privateKey, BigInteger publicKey,
                BigInteger nKey, int balance, int userRole) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.nKey = nKey;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.balance = balance;
        this.userRole = userRole;
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

    public int getUserRole () { return userRole; }

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
