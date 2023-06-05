package it.unibz.infosec.examproject.user.application;

import it.unibz.infosec.examproject.user.domain.ManageUsers;
import it.unibz.infosec.examproject.user.domain.SearchUsers;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    private final ManageUsers manageUsers;
    private final SearchUsers searchUsers;

    @Autowired
    public UserController (ManageUsers manageUsers, SearchUsers searchUsers) {
        this.manageUsers = manageUsers;
        this.searchUsers = searchUsers;
    }

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable("id") Long id) {
        return manageUsers.readUser(id);
    }

//    @PostMapping("/create")
//    public UserEntity createNewUser(@RequestBody CreateUserDTO dto) {
//        return manageUsers.createUser(dto.getEmail(), dto.getPassword());
//    }

    @PostMapping("/update/{id}/{balance}")
    public UserEntity updateUser(@PathVariable("id") Long id,@PathVariable("balance") int balance){
        return manageUsers.updateUser(id, balance);
    }

    @DeleteMapping("/delete/{id}")
    public UserEntity deleteUser(@PathVariable("id") Long id)  {
        return manageUsers.deleteUser(id);
    }

    @GetMapping("/getAll")
    public List<UserEntity> findAll(){
        return searchUsers.findAll();
    }

    @PostMapping("/transfer/{id}/{email}/{amount}")
    public UserEntity transferMoney(@PathVariable("id") Long id, @PathVariable("email") String email, @PathVariable("amount") int amount) {
        return manageUsers.sendAmount(id, email, amount);
    }
    
}
