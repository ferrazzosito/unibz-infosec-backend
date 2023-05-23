package it.unibz.infosec.examproject.user.domain;

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


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    private String password;

    private String salt;

    private BigInteger publicKey;

    private BigInteger privateKey;

    private BigInteger nKey;

    private int balance;


    public UserEntity(String email, String password, String salt, BigInteger privateKey, BigInteger publicKey, BigInteger nKey, int balance, List<Role> roles) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.nKey = nKey;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.balance = balance;
        this.roles = roles;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> role) {
        this.roles = role;
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
