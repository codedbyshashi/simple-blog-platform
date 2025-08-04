package com.blogplatform.simpleblogplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // --- NEW AUTHORIZATION RULE ---
                        // Rule 1: Secure the Admin Dashboard.
                        // This is our most specific rule, so it must come first.
                        // Any URL starting with /admin/ now requires the user to have the 'ADMIN' role.
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Existing Public Access Rules: These rules come after the more specific admin rule.
                        .requestMatchers("/", "/posts/**", "/register", "/css/**", "/js/**").permitAll()

                        // Rule 2: The Catch-All Rule.
                        // Any other request that hasn't been matched yet must be authenticated.
                        // This is a crucial security best practice (secure by default).
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/")
                );

        return http.build();
    }
}