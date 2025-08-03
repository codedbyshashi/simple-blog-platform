package com.blogplatform.simpleblogplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // NEW: Import HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // NEW: Import SecurityFilterChain

/**
 * SecurityConfig is the central place for configuring all security-related
 * aspects of the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- NEW: Define the SecurityFilterChain bean ---
    /**
     * Configures the security filter chain that defines the application's security rules.
     * This is the central place to configure which URLs are public and which are protected.
     *
     * @param http The HttpSecurity object to be configured. Spring automatically provides this.
     * @return The configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .authorizeHttpRequests() is where we start configuring URL-based authorization.
                .authorizeHttpRequests(authorize -> authorize
                        // Use requestMatchers to specify URL patterns. We use Ant-style path patterns.
                        // "/", "/posts/**", "/register", "/css/**", "/js/**" are made public.
                        // - "/": The home page.
                        // - "/posts/**": Any URL starting with /posts, allowing access to post detail pages like /posts/1, /posts/2, etc.
                        // - "/register": The user registration page.
                        // - "/css/**", "/js/**": All CSS and JavaScript files must be public for the site to render correctly.
                        .requestMatchers("/", "/posts/**", "/register", "/css/**", "/js/**").permitAll()

                        // .anyRequest().authenticated() is a catch-all rule. It specifies that any request
                        // that has not been explicitly permitted above must be authenticated. This is a secure-by-default
                        // approach, ensuring no endpoints are accidentally left unprotected.
                        .anyRequest().authenticated()
                )
                // .formLogin() configures form-based authentication.
                .formLogin(formLogin -> formLogin
                        // .loginPage("/login") specifies the URL of our custom login page.
                        // Spring Security will automatically redirect unauthenticated users trying to access
                        // a protected page to this URL.
                        .loginPage("/login")

                        // We must also permit all traffic to the login page itself.
                        .permitAll()
                )
                // .logout() configures logout behavior.
                .logout(logout -> logout
                        // .permitAll() allows any user to access the logout functionality.
                        .permitAll()
                        // You can also specify a URL to redirect to after logout.
                        .logoutSuccessUrl("/")
                );

        // Build and return the configured HttpSecurity object.
        return http.build();
    }
}