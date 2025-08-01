package com.blogplatform.simpleblogplatform.service;

import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // NEW: Import LocalDateTime for timestamping
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
     * @return a List of all Post objects.
     */
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Finds a single Post by its ID.
     * @param id The primary key of the post to find.
     * @return The found Post object.
     * @throws RuntimeException if no post is found with the given ID.
     */
    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    // --- NEW: Implement the method to save (create or update) a post ---
    /**
     * Saves a Post entity. This method handles both creation of new posts
     * and updates to existing ones.
     *
     * @param post The Post object to save. If post.id is null, it's a new post.
     * @return The saved Post entity, which will include the auto-generated ID if it was a new post.
     */
    public Post savePost(Post post) {
        // Enforce a business rule: set the creation timestamp only for new posts.
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        // Delegate the actual save operation to the repository.
        // The save() method intelligently handles both INSERT and UPDATE.
        return postRepository.save(post);
    }
}