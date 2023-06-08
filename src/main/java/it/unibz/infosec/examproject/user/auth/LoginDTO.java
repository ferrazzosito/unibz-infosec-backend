package it.unibz.infosec.examproject.user.auth;

import lombok.Data;

@Data
public class LoginDTO {

    private String email;
    private String password;

}
