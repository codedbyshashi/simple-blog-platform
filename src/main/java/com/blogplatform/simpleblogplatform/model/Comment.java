package com.blogplatform.simpleblogplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Represents a comment entity made by a user on a post.
 * This entity is fully relational, linked to both a Post and a User (the author).
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // --- NEW: Defining the relationship to the User entity (the author) ---

    /**
     * Establishes a many-to-one relationship between Comment and User.
     * Many comments can be written by a single user.
     * This makes the Comment entity the owning side of this relationship.
     */
    @ManyToOne
    /**
     * Specifies the foreign key column in the 'comment' table for this relationship.
     * The column will be named 'user_id' and will store the ID of the author.
     * `nullable = false` ensures that a comment must always have an author.
     */
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment() {
    }

    // --- Getters and Setters ---
    // (Existing getters and setters for id, content, createdAt, post)

    // --- NEW: Getters and setters for the user field ---

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // The rest of the existing getters and setters remain unchanged below.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        // We can update the toString for even better debugging.
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", postId=" + (post != null ? post.getId() : "null") +
                ", userId=" + (user != null ? user.getId() : "null") + // Add author's ID
                '}';
    }
}