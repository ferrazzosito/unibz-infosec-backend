package it.unibz.infosec.examproject.util.config;

import it.unibz.infosec.examproject.user.domain.CustomUserDetailsService;
import it.unibz.infosec.examproject.user.domain.access.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private CustomUserDetailsService userDetailsService;

    @Autowired
    public AppConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService =userDetailsService;
    }

    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }
}
