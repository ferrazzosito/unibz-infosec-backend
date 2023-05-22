package it.unibz.infosec.examproject.user.application;

import it.unibz.infosec.examproject.user.domain.ManageUsers;
import it.unibz.infosec.examproject.user.domain.SearchUsers;
import it.unibz.infosec.examproject.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    private final ManageUsers manageUsers;
    private final SearchUsers searchUsers;

    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @Autowired
    public UserController (ManageUsers manageUsers, SearchUsers searchUsers) {
        this.manageUsers = manageUsers;
        this.searchUsers = searchUsers;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return manageUsers.readUser(id);
    }

    @PostMapping("/registration")
    public User createNewUser(@RequestBody CreateUserDTO dto) {
        return manageUsers.createUser(dto.getEmail(), dto.getPassword(), dto.getUserRole());
    }


//    @PostMapping("/login")
//    public void login(@RequestBody LoginUserDTO loginRequest, HttpServletRequest request, HttpServletResponse response) {
//        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
//                loginRequest.getEmail(), loginRequest.getPassword());
//
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
//        context.setAuthentication(authentication);
//        securityContextHolderStrategy.setContext(context);
//        securityContextRepository.saveContext(context, request, response);
//    }


//    @PostMapping("/update/{id}")
//    public User updateUser(@PathVariable("id") Long id,@RequestBody UpdateUserDTO dto){
//        return manageUsers.updateUser(id, dto.getBalance()); //?
//    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable("id") Long id)  {
        return manageUsers.deleteUser(id);
    }

    @GetMapping("/getAll")
    public List<User> findAll(){
        return searchUsers.findAll();
    }

}
