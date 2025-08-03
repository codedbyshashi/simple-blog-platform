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

    // --- NEW: Declare the dependency as a private final field. ---
    // This tells us that every instance of CustomUserDetailsService MUST have a UserRepository.
    // 'final' ensures it's assigned once and cannot be changed later.
    private final UserRepository userRepository;

    // --- NEW: Use Constructor Injection to provide the dependency. ---
    // This is the single constructor for our service. Spring sees this and knows
    // that to create a CustomUserDetailsService bean, it must first find a UserRepository
    // bean to "inject" into this constructor.
    // Since Spring 4.3, if a class has only one constructor, @Autowired is not needed.
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is now fully functional because the 'userRepository' field
     * will be initialized by Spring.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}