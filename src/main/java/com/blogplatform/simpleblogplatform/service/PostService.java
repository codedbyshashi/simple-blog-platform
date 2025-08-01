package com.blogplatform.simpleblogplatform.service;

import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The PostService class contains the business logic for Post-related operations.
 * It uses the PostRepository to interact with the database.
 */
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Retrieves all Post entities from the database.
     * This method delegates the call directly to the PostRepository's findAll() method.
     * @return a List of all Post objects.
     */
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    // --- NEW: Implement the method to find a single post by its ID ---
    /**
     * Finds a single Post by its ID.
     * This method leverages the findById method from JpaRepository, which returns an Optional.
     *
     * @param id The primary key of the post to find.
     * @return The found Post object.
     * @throws RuntimeException if no post is found with the given ID. This signals a "not found" state.
     */
    public Post findPostById(Long id) {
        // The findById() method executes a "SELECT * FROM post WHERE id = ?" query.
        // It returns an Optional<Post> to gracefully handle the case where no post matches the ID.
        return postRepository.findById(id)
                // The orElseThrow() method is the perfect tool for this scenario.
                // If the Optional contains a Post, it returns the Post.
                // If the Optional is empty, it throws the exception provided by the lambda expression.
                // For now, we use a generic RuntimeException. Later in the project, we will create
                // a custom 'ResourceNotFoundException' for more specific global error handling.
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }
}