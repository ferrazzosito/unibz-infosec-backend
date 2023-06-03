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
    public UserController(ManageUsers manageUsers, SearchUsers searchUsers) {
        this.manageUsers = manageUsers;
        this.searchUsers = searchUsers;
    }

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable("id") Long id) {
        return manageUsers.readUser(id);
    }

    @PostMapping("/update/{id}")
    public UserEntity updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO dto){
        return manageUsers.updateUser(id, dto.getBalance()); //?
    }

    @DeleteMapping("/delete/{id}")
    public UserEntity deleteUser(@PathVariable("id") Long id)  {
        return manageUsers.deleteUser(id);
    }

    @GetMapping("/getAll")
    public List<UserEntity> findAll(){
        return searchUsers.findAll();
    }
}
