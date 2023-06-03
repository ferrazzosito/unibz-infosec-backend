package it.unibz.infosec.examproject.config;

import it.unibz.infosec.examproject.security.CustomAuthenticationProvider;
import it.unibz.infosec.examproject.security.CustomUserDetailsService;
import it.unibz.infosec.examproject.security.JwtAuthEntryPoint;
import it.unibz.infosec.examproject.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomAuthenticationProvider authProvider;
    private CustomUserDetailsService customUserDetailsService;

    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer.httpStrictTransportSecurity(
                        HeadersConfigurer.HstsConfig::disable));
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .anyRequest()
                        .permitAll());
        http.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(
                        request -> new CorsConfiguration().applyPermitDefaultValues()));
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
    public JwtAuthenticationFilter jwtAuthenticationFilter () {
        return new JwtAuthenticationFilter();
    }

}
