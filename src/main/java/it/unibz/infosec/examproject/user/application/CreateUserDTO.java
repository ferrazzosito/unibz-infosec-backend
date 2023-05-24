package it.unibz.infosec.examproject.user.application;


public class CreateUserDTO {

    private String email;
    private String password;
    private String salt;
    private int balance;

    public CreateUserDTO() {}

    //TODO:aggiungere role (ma solo Client o Vendor)
    public CreateUserDTO(String email, String password, String salt, int balance) {
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
