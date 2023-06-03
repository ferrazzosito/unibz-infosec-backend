package it.unibz.infosec.examproject.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SafeUserEntity {

    private String email;
    private String _role;

    public SafeUserEntity(String email, String role) {
        this.email = email;
        this._role = role;
    }
}
