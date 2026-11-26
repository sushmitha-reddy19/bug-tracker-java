package com.sushmitha.bugtracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${app.auth.username}") private String username;
    @Value("${app.auth.password}") private String password;

    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/health").permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean InMemoryUserDetailsManager users() { return new InMemoryUserDetailsManager(User.withUsername(username).password("{noop}" + password).roles("USER").build()); }
}
