package it.unibz.infosec.examproject.user.auth;

import it.unibz.infosec.examproject.product.domain.UnsafeProductRepository;
import it.unibz.infosec.examproject.security.JwtGenerator;
import it.unibz.infosec.examproject.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final UnsafeUserRepository unsafeUserRepository;

    private final ManageUsers manageUsers;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          UnsafeUserRepository unsafeProductRepository,
                          ManageUsers manageUsers,
                          JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.unsafeUserRepository = unsafeProductRepository;
        this.manageUsers = manageUsers;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }
        final Role role = Role.fromString(registerDTO.getRole());
        if (role == null) {
            return new ResponseEntity<>("Role is not valid!", HttpStatus.BAD_REQUEST);
        }
        manageUsers.createUser(registerDTO.getEmail(), registerDTO.getPassword(), role);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                        loginDTO.getPassword()));
        final UserEntity user = this.userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();
        final String token = jwtGenerator.generateToken(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
}
