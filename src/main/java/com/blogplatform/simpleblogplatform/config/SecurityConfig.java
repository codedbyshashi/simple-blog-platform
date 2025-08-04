package com.blogplatform.simpleblogplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // NEW: Import HttpMethod
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
                                // --- NEW AUTHORIZATION RULES START ---

                                // Rule 1: Secure the Admin Dashboard
                                // Any URL starting with /admin/ requires the user to have the 'ADMIN' role.
                                // This is our most specific rule, so it comes first.
                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                // Rule 2: Secure Comment Creation
                                // To create a comment, a user will likely POST to an endpoint. Let's assume
                                // this endpoint is /posts/{postId}/comments. We only want authenticated
                                // users with the 'USER' role to be able to do this.
                                // We specify HttpMethod.POST to make the rule precise.
                                .requestMatchers(HttpMethod.POST, "/posts/*/comments").hasRole("USER")

                                // Rule 3: Refine Public Access
                                // We will now be more specific about public URLs. Anyone can view the homepage,
                                // list of posts, and individual posts using the GET method.
                                .requestMatchers(HttpMethod.GET, "/", "/posts", "/posts/**").permitAll()

                                // Other public pages like registration and static resources remain fully public.
                                .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()

                                // Rule 4: The Catch-All Rule
                                // Any other request that hasn't been matched yet must be authenticated.
                                // This is a crucial security best practice (secure by default).
                                .anyRequest().authenticated()

                        // --- NEW AUTHORIZATION RULES END ---
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