package com.blogplatform.simpleblogplatform.config;

import org.springframework.context.annotation.Bean; // NEW: Import the @Bean annotation
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // NEW: Import BCrypt
import org.springframework.security.crypto.password.PasswordEncoder; // NEW: Import the interface

/**
 * SecurityConfig is the central place for configuring all security-related
 * aspects of the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // --- NEW: Define the PasswordEncoder bean ---
    /**
     * Defines the PasswordEncoder bean that will be used for hashing passwords.
     * We are using BCrypt, which is a strong, modern, and adaptive hashing algorithm.
     * By defining this as a bean, it can be injected into other parts of our
     * application, such as the UserService, to encode passwords before saving them.
     * Spring Security will also automatically use this bean for password verification
     * during the authentication process.
     *
     * @return An instance of BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder is the recommended implementation for password hashing.
        // It automatically handles salting to protect against rainbow table attacks.
        return new BCryptPasswordEncoder();
    }

}