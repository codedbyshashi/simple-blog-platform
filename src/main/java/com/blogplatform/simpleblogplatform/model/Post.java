package com.blogplatform.simpleblogplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// Import the necessary annotations for defining relationships.
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

/**
 * Represents a blog post entity.
 * This class now includes a relationship to the User entity, representing the author.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    // --- NEW: Defining the relationship to the User entity ---

    /**
     * The @ManyToOne annotation establishes a many-to-one relationship
     * between Post and User. This means many posts can belong to one user (the author).
     * By default, this relationship is EAGERLY fetched, meaning that whenever a Post is loaded,
     * its associated User will also be loaded from the database immediately.
     */
    @ManyToOne
    /**
     * The @JoinColumn annotation specifies the foreign key column in the 'post' table.
     * The 'name' attribute defines the name of the foreign key column, which will be 'user_id'.
     * The 'referencedColumnName' attribute (which we are omitting here for simplicity,
     * as JPA can infer it) would specify which column in the 'user' table this foreign key
     * refers to (by default, it's the primary key, 'id').
     */
    @JoinColumn(name = "user_id")
    private User user; // This field represents the author of the post.

    public Post() {
    }

    // --- Getters and Setters ---

    // ... (existing getters and setters for id, title, content, createdAt)

    // --- NEW: Getters and setters for the user field ---

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // The existing getters and setters remain unchanged below.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}