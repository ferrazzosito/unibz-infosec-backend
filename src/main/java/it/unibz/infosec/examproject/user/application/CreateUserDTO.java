package it.unibz.infosec.examproject.user.application;

public class CreateUserDTO {

    private String email;
    private String password;
    private String salt;
    private int balance;
    private int type;

    public CreateUserDTO() {}

    public CreateUserDTO(String email, String password, String salt, int balance, int type) {
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.balance = balance;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
