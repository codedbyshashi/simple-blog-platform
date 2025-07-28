package com.blogplatform.simpleblogplatform.model;

import jakarta.persistence.Entity;
// Import the necessary annotations for primary key mapping and generation.
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Represents a blog post entity.
 * The @Entity annotation marks this class as a JPA entity,
 * telling Hibernate to create a 'post' table for it in the database.
 */
@Entity
public class Post {

    // The @Id annotation specifies the primary key of an entity.
    @Id
    // @GeneratedValue provides for the specification of generation strategies for the values of primary keys.
    // GenerationType.IDENTITY indicates that the persistence provider must assign primary keys for the entity
    // using a database identity column. This is the most common strategy for auto-incrementing columns.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The title of the blog post.
    private String title;

    // The main content of the blog post. In a real database, this would typically
    // be mapped to a TEXT or CLOB type to allow for very long content.
    private String content;

    // The date and time when the post was created.
    private LocalDateTime createdAt;

    // A no-argument constructor is required by JPA.
    public Post() {
    }

    // --- Getters and Setters ---

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