package it.unibz.infosec.examproject.user.auth;

import lombok.Data;

@Data
public class RegisterDTO {

    private String email;
    private String password;
    private String role;

}
