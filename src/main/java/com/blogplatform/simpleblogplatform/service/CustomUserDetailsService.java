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

/**
 * This service is responsible for loading user-specific data.
 * It is used by the Spring Security framework for authentication and authorization.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Inject the UserRepository to fetch user data from the database.
    private final UserRepository userRepository;

    // Use constructor injection for the dependency.
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
        // 1. Fetch our custom User entity from the database using the repository.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));

        // 2. Get the user's role and convert it into a GrantedAuthority.
        // Spring Security requires roles to be in the format 'ROLE_YOUR_ROLE'.
        // We will store roles as "ADMIN" or "USER" in the database and add the prefix here.
        // For now, we will use the role from the User entity. If the role field is empty,
        // we can assign a default role. Let's create a simple authority list.
        // In the next step on Role-Based Access Control, we will make this more robust.
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

        // 3. Create and return a Spring Security UserDetails object.
        // This is an object that Spring Security understands. We map the fields from our
        // custom User entity to this standard security User object.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}