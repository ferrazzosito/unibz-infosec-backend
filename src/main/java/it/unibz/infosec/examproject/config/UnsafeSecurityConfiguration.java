package it.unibz.infosec.examproject.config;

import it.unibz.infosec.examproject.security.CustomAuthenticationProvider;
import it.unibz.infosec.examproject.security.CustomUserDetailsService;
import it.unibz.infosec.examproject.security.JwtAuthEntryPoint;
import it.unibz.infosec.examproject.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class UnsafeSecurityConfiguration {

    @Autowired
    private CustomAuthenticationProvider authProvider;
    private CustomUserDetailsService customUserDetailsService;

    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    public UnsafeSecurityConfiguration(CustomUserDetailsService customUserDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authProvider);
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/auth/**")
                        .permitAll()
                        .requestMatchers("/chat/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}
