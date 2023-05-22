package it.unibz.infosec.examproject.user.application;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginUserDTO {

    private String email;
    private String password;
}
