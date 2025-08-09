package com.blogplatform.simpleblogplatform.service;

import com.blogplatform.simpleblogplatform.dto.CommentDto;
import com.blogplatform.simpleblogplatform.model.Comment;
import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.model.User;
import com.blogplatform.simpleblogplatform.repository.CommentRepository;
import com.blogplatform.simpleblogplatform.repository.PostRepository;
import com.blogplatform.simpleblogplatform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // Inject all required repositories via the constructor
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates and saves a new comment.
     * This method contains the business logic for associating the comment
     * with the correct post and user.
     *
     * @param postId The ID of the post to which the comment is being added.
     * @param username The username of the author of the comment.
     * @param commentDto The DTO containing the comment's content.
     */
    @Transactional // Ensures this entire operation is a single database transaction
    public void saveComment(Long postId, String username, CommentDto commentDto) {
        // Step 1: Fetch the Post entity. If not found, an exception is thrown.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));

        // Step 2: Fetch the User entity (the author). If not found, an exception is thrown.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));

        // Step 3: Create a new Comment entity from the DTO and the fetched entities.
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post); // Associate the comment with the post
        comment.setUser(user); // Associate the comment with the user (author)

        // Step 4: Save the new comment to the database.
        commentRepository.save(comment);
    }
}