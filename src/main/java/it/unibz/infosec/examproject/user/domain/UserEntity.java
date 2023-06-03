package it.unibz.infosec.examproject.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String _role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    private BigInteger publicKey;

    private BigInteger nKey;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigInteger privateKey;

    private int balance;

    public UserEntity(String email, String password, String salt, BigInteger privateKey, BigInteger publicKey, BigInteger nKey, int balance, String role) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.nKey = nKey;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.balance = balance;
        this._role = role;
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

    public Role getRole() {
        return Role.fromString(_role);
    }

    public void setRole(String role) {
        this._role = role;
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
