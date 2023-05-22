package it.unibz.infosec.examproject.user.application;

import java.sql.Date;

public class UpdateUserDTO extends CreateUserDTO {

    private Long id;
    public UpdateUserDTO() {super();}

    public UpdateUserDTO(Long id, String email, String password, String salt, int balance, int type){

//        super(email, password, salt, type);
        //TODO: to be fixed
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}
}
