package it.unibz.infosec.examproject.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SafeUserEntity {

    private String email;
    private String role;
    private int balance;

    public SafeUserEntity(String email, String role, int balance) {
        this.email = email;
        this.role = role;
        this.balance = balance;
    }
}
