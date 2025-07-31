package com.blogplatform.simpleblogplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// Import the necessary annotations and the Post class for the relationship.
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Represents a comment entity made by a user on a post.
 * This is the OWNING side of the bidirectional relationship with Post.
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    // --- NEW: Defining the relationship to the Post entity ---

    /**
     * The @ManyToOne annotation establishes a many-to-one relationship.
     * This means many comments can be associated with a single post.
     * This is the OWNING side of the relationship because it contains the foreign key.
     */
    @ManyToOne
    /**
     * The @JoinColumn annotation specifies the foreign key column in this entity's table ('comment').
     * 'name = "post_id"' explicitly names the foreign key column 'post_id'.
     * This column will store the 'id' of the Post to which this comment belongs.
     * 'nullable = false' is an optional but good practice constraint, ensuring that a comment
     * can NEVER exist without being associated with a post.
     */
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
    }

    // --- Getters and Setters ---
    // (Existing getters and setters for id, content, createdAt)

    // --- NEW: Getters and setters for the post field ---

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    @Override
    public String toString() {
        // We can update the toString to include the post's ID for better debugging.
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", postId=" + (post != null ? post.getId() : "null") + // Avoid NullPointerException
                '}';
    }
}