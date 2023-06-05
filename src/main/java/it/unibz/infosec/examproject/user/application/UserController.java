package it.unibz.infosec.examproject.user.application;

import it.unibz.infosec.examproject.user.domain.*;
import it.unibz.infosec.examproject.util.RESTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final ManageUsers manageUsers;
    private final SearchUsers searchUsers;

    @Autowired
    public UserController(UserRepository userRepository, ManageUsers manageUsers, SearchUsers searchUsers) {
        this.userRepository = userRepository;
        this.manageUsers = manageUsers;
        this.searchUsers = searchUsers;
    }

    @GetMapping("/{id}")
    public SafeUserEntity findById(@PathVariable("id") Long id) {
        final UserEntity user = manageUsers.readUser(id);
        return new SafeUserEntity(user.getEmail(), user.getRole().getName());
    }

    @GetMapping("/me")
    public UserEntity getSelf() {
        return manageUsers.readUser(RESTUtils.getLoggedUser(userRepository).getId());
    }

    @PostMapping("/update/{id}")
    public UserEntity updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO dto) {
        return manageUsers.updateUser(id,
                RESTUtils.getLoggedUser(userRepository).getId(), dto.getBalance());
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
