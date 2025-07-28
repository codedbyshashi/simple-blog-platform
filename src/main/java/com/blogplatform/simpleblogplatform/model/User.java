package com.blogplatform.simpleblogplatform.model;

/**
 * Represents a user entity in the application.
 * This POJO defines the structure for a user account, which will be fundamental
 * for authentication and authorization.
 */
public class User {

    // A unique identifier for the user. As with the Post entity, we use the Long
    // wrapper class so it can be null before the entity is saved to the database.
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