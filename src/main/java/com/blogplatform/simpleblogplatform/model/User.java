package com.blogplatform.simpleblogplatform.model;

// Import the necessary JPA annotations.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a user entity in the application.
 * The @Entity annotation makes this a managed JPA entity.
 */
@Entity // <-- NEW: Mark this class as a JPA entity.
public class User {

    // @Id marks this field as the primary key.
    @Id
    // @GeneratedValue configures the primary key generation strategy.
    // GenerationType.IDENTITY delegates ID generation to the database's auto-increment column.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- NEW: Configure auto-generation.
    private Long id;

    // The user's unique name for logging in. We will later add a database constraint
    // to ensure no two users can have the same username.
    private String username;

    // The user's password. CRITICAL: We will NEVER store this as plain text.
    // This field will hold a cryptographically hashed version of the password.
    // The actual hashing will be handled by Spring Security later.
    private String password;

    // The user's role, which determines their permissions (e.g., "ROLE_USER", "ROLE_ADMIN").
    // This is the foundation of our authorization logic.
    private String role;

    // A no-argument constructor, required by JPA for creating entity instances
    // when fetching them from the database.
    public User() {
    }

    // --- Getters and Setters ---
    // Standard JavaBeans convention for providing controlled access to private fields.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}