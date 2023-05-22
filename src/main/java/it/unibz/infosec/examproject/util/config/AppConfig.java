package it.unibz.infosec.examproject.util.config;

import it.unibz.infosec.examproject.user.domain.access.ActiveUserStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }
}
