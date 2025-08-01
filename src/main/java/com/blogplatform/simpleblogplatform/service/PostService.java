package com.blogplatform.simpleblogplatform.service;

// Import the necessary classes
import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List; // NEW: Import the List interface from java.util

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

    // --- NEW: Implement the method to find all posts ---
    /**
     * Retrieves all Post entities from the database.
     * This method delegates the call directly to the PostRepository's findAll() method.
     * The service layer here acts as a pass-through for this simple operation,
     * but could contain more complex logic in a real-world scenario (e.g., filtering, sorting).
     *
     * @return a List of all Post objects. The list will be empty if no posts are found, not null.
     */
    public List<Post> findAllPosts() {
        // The findAll() method is provided by the JpaRepository interface.
        // Spring Data JPA automatically implements this method for us at runtime.
        // It executes a "SELECT * FROM post" query (or its Hibernate JPQL equivalent)
        // and maps each row of the result set to a Post object, collecting them into a List.
        return postRepository.findAll();
    }
}