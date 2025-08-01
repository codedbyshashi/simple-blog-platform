package com.blogplatform.simpleblogplatform.service;

// Import the PostRepository so we can use it.
import com.blogplatform.simpleblogplatform.repository.PostRepository;
import org.springframework.stereotype.Service;

/**
 * The PostService class contains the business logic for Post-related operations.
 * It uses the PostRepository to interact with the database.
 */
@Service
public class PostService {

    // --- NEW: Declare the dependency ---
    // We declare the repository as a private and final field.
    // 'final' ensures that it's initialized only once, via the constructor,
    // making our service more robust.
    private final PostRepository postRepository;

    // --- NEW: Use Constructor Injection ---
    // Spring will automatically "inject" an instance of PostRepository
    // into this constructor when creating the PostService bean.
    // Since Spring 4.3, if a class has only one constructor, the @Autowired
    // annotation can be omitted. This is the recommended approach.
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // In the next tasks, we will add methods here that use the postRepository.
}