package com.blogplatform.simpleblogplatform.model;

// Import the necessary new classes for the collection and the relationship.
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Represents a blog post entity.
 * This class now includes relationships to its author (User) and its comments.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // --- NEW: Defining the relationship to the Comment entity ---

    /**
     * The @OneToMany annotation establishes a one-to-many relationship
     * between this Post and many Comments.
     *
     * `mappedBy = "post"`: This is the most critical part. It tells Hibernate that
     * this side (Post) is the INVERSE side of the relationship and that the
     * OWNING side is the `Comment` entity. The mapping details (like the join column)
     * should be looked for on the 'post' field within the `Comment` class.
     * This prevents the creation of a separate join table.
     *
     * Fetching: By default, @OneToMany relationships are LAZILY fetched. This means
     * the list of comments will only be loaded from the database when we explicitly
     * access it (e.g., by calling post.getComments()). This is a performance optimization.
     */
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>(); // Initialize to avoid NullPointerExceptions

    public Post() {
    }

    // --- Getters and Setters ---
    // (Existing getters and setters for id, title, content, createdAt, user)

    // --- NEW: Getters and setters for the comments list ---
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // The rest of the existing getters and setters remain unchanged below.
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post orElseThrow(Object o) {
        return null;
    }
}