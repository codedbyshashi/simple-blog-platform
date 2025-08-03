package com.blogplatform.simpleblogplatform.service;

import com.blogplatform.simpleblogplatform.model.User;
import com.blogplatform.simpleblogplatform.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This service is responsible for loading user-specific data.
 * It is used by the Spring Security framework for authentication and authorization.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is called by Spring Security to load a user by their username.
     * @param username The username of the user trying to log in.
     * @return A UserDetails object that Spring Security can use for authentication.
     * @throws UsernameNotFoundException if the user is not found in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // --- NEW LOGIC START ---

        // Step 1: Fetch our custom User entity from the database.
        // We use the findByUsername method we defined in our UserRepository.
        // This method returns an Optional<User>, which is a container that may or may not
        // hold a non-null User object. This is a modern, null-safe way to handle lookups.
        User user = userRepository.findByUsername(username)
                // Step 2: Handle the case where the user is not found.
                // The orElseThrow() method is called on the Optional. If the Optional is empty,
                // it executes the provided lambda, which throws the required exception.
                // This fulfills the contract of the UserDetailsService.
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));

        // Step 3: Convert the user's role into a collection of GrantedAuthority objects.
        // Spring Security uses GrantedAuthority to represent permissions. The standard convention
        // is to prefix roles with "ROLE_". This allows us to use role-based security expressions
        // like .hasRole("ADMIN") in our security configuration.
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
        );

        // Step 4: Create and return a Spring Security UserDetails object.
        // We use the built-in org.springframework.security.core.userdetails.User class,
        // which is a convenient implementation of the UserDetails interface.
        // We provide it with the three essential pieces of information for authentication:
        // 1. Username: The user's principal name.
        // 2. Password: The user's HASHED password from our database. Spring Security will
        //    compare this against a hash of the password the user submitted.
        // 3. Authorities: The collection of roles/permissions the user has.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
        // --- NEW LOGIC END ---
    }
}