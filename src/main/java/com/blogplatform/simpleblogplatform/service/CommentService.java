package com.blogplatform.simpleblogplatform.service;

// Import the CommentRepository so we can declare it as a dependency.
import com.blogplatform.simpleblogplatform.repository.CommentRepository;
import org.springframework.stereotype.Service;

/**
 * The CommentService class encapsulates the business logic for all operations
 * related to comments. It uses the CommentRepository to interact with the database.
 */
@Service
public class CommentService {

    // --- NEW: Declare the repository as a final field ---
    // This establishes that CommentService has a dependency on CommentRepository.
    // 'private' ensures encapsulation.
    // 'final' ensures that the dependency is immutable once assigned via the constructor.
    private final CommentRepository commentRepository;

    // --- NEW: Implement Constructor Injection ---
    // This is the constructor for the CommentService. Spring's IoC container will see this
    // and know that to create a CommentService bean, it must first provide a CommentRepository bean.
    // Since Spring 4.3, if a class has only one constructor, the @Autowired annotation is implicit and can be omitted.
    public CommentService(CommentRepository commentRepository) {
        // Assign the injected repository bean to our final field.
        this.commentRepository = commentRepository;
    }

    // In the next task, we will add methods here that use the injected commentRepository.
}