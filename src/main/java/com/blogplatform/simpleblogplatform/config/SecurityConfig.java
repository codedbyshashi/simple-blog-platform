package com.blogplatform.simpleblogplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * SecurityConfig is the central place for configuring all security-related
 * aspects of the application. By annotating it with @Configuration and
 * @EnableWebSecurity, we activate Spring Security's web security support
 * and provide our custom configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // In the upcoming tasks, this class will become the heart of our application's security.
    // We will define beans here for:
    // 1. PasswordEncoder: To securely hash user passwords.
    // 2. UserDetailsService: To tell Spring Security how to find our users.
    // 3. SecurityFilterChain: To configure which URLs are public and which are protected.
    // For now, its existence sets up the foundation for all future security work.
}