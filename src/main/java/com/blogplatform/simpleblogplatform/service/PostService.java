package com.blogplatform.simpleblogplatform.service;

import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    /**
     * Saves a Post entity. This method handles both creation of new posts
     * and updates to existing ones.
     * @param post The Post object to save. If post.id is null, it's a new post.
     * @return The saved Post entity, which will include the auto-generated ID if it was a new post.
     */
    public Post savePost(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        return postRepository.save(post);
    }

    // --- NEW: Implement the method to delete a post ---\
    /**
     * Deletes a Post from the database by its ID.
     * This method delegates directly to the repository's deleteById method.
     *
     * @param id The primary key of the post to be deleted.
     */
    public void deletePostById(Long id) {
        // The deleteById() method is provided by JpaRepository.
        // It executes a \"DELETE FROM post WHERE id = ?\" query.
        // It has a void return type, as it does not return any data.
        postRepository.deleteById(id);
    }
}