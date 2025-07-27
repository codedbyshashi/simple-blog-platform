package com.blogplatform.simpleblogplatform.model;

// Import the Entity annotation from the Jakarta Persistence package.
// This is the standard API for persistence in modern Spring Boot applications.
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

/**
 * Represents a blog post entity.
 * The @Entity annotation marks this class as a JPA entity,
 * telling Hibernate to create a 'post' table for it in the database.
 */
@Entity // <-- THIS IS THE NEW ANNOTATION
public class Post {

    // A unique identifier for the post. We use the Long wrapper class instead of the
    // primitive long type because it can be null. This is useful for JPA to know
    // if an entity is new (id is null) or already exists in the database.
    private Long id;

    // The title of the blog post.
    private String title;

    // The main content of the blog post. In a real database, this would typically
    // be mapped to a TEXT or CLOB type to allow for very long content.
    private String content;

    // The date and time when the post was created.
    // We use LocalDateTime from Java 8's Date and Time API (`java.time`) because
    // it's immutable and provides a more precise and developer-friendly API
    // than the old `java.util.Date`.
    private LocalDateTime createdAt;

    // A no-argument constructor is required by JPA.
    // JPA uses this constructor to create instances of the entity when fetching them
    // from the database, and then populates the fields using reflection.
    public Post() {
    }

    // --- Getters and Setters ---
    // These methods provide controlled access to the private fields of the class.
    // This principle is known as Encapsulation, a core concept of Object-Oriented Programming.

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