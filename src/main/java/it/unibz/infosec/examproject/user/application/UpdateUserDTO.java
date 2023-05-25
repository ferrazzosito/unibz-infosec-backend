package it.unibz.infosec.examproject.user.application;


public class UpdateUserDTO extends CreateUserDTO {

    private Long id;
    public UpdateUserDTO() {super();}

    public UpdateUserDTO(Long id, String email, String password, String salt, int balance){

        super(email, password, salt, balance);
        this.id=id;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}
}
