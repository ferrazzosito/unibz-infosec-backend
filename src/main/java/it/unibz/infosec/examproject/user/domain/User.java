package it.unibz.infosec.examproject.user.domain;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String salt;

    private int balance;

    private int type;

    public User() {}
    public User(String email, String password, String salt, int balance, int type) {
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
}
