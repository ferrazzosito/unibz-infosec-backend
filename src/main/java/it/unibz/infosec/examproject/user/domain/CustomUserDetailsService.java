package it.unibz.infosec.examproject.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not Foudn"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getUserRole()));
    }


    //Here the stuff of authorities is that I have int but I should have an enum and I dont know if theres any link between enum names
    //and the way spring it manages that
    private Collection<GrantedAuthority> mapRolesToAuthorities(int role) {
        List<Integer> roles = List.of(role);
        return roles.stream().map(rl -> new SimpleGrantedAuthority(rl.toString())).collect(Collectors.toList());
    }

}
