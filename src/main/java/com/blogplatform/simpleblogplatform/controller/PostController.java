package com.blogplatform.simpleblogplatform.controller;

import com.blogplatform.simpleblogplatform.dto.CommentDto;
import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.service.CommentService; // NEW: Import CommentService
import com.blogplatform.simpleblogplatform.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // NEW: Import ModelAttribute
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // NEW: Import PostMapping

import java.security.Principal; // NEW: Import Principal
import java.util.List;

/**
 * PostController handles all public-facing web requests related to blog posts.
 * This includes displaying the list of all posts (homepage) and viewing a single post.
 */
@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Post> allPosts = postService.findAllPosts();
        model.addAttribute("posts", allPosts);
        return "home";
    }

    // --- NEW METHOD START ---

    /**
     * Handles GET requests to view a single post's detail page.
     *
     * @param id The ID of the post, extracted from the URL path.
     * @param model The Model object to pass data to the view.
     * @return The logical name of the view template ("post-detail").
     */
    @GetMapping("/posts/{id}")
    public String showPostDetailPage(@PathVariable Long id, Model model) {
        // Step 1: Use the 'id' to fetch the specific post from the PostService.
        // It's a best practice for service methods to return an Optional to handle
        // the case where a post with the given ID doesn't exist.
        Post post = postService.findPostById(id);
                // If the post is not found, we could throw an exception which could
                // be handled by a @ControllerAdvice to show a custom 404 page.
                // For now, we'll assume the post exists. A more robust implementation
                // will be added in a later step (Error Handling).


        // Step 2: Add the found Post object to the model.
        // We'll use the key "post" to access this object in our Thymeleaf template.
        model.addAttribute("post", post);

        // Step 3: Return the name of the detail view template.
        // Spring's ViewResolver will look for a template named "post-detail.html".
        model.addAttribute("newComment", new CommentDto());
        return "post-detail";
    }
    // --- NEW METHOD END ---
    // --- NEW METHOD START ---

    /**
     * Handles the submission of the new comment form.
     *
     * @param postId      The ID of the post, captured from the URL path.
     * @param commentDto  The comment data, bound from the submitted form.
     * @param principal   The currently authenticated user, injected by Spring Security.
     * @return A redirect instruction to the post detail page.
     */
    @PostMapping("/posts/{postId}/comments")
    public String submitComment(@PathVariable Long postId,
                                @ModelAttribute("newComment") CommentDto commentDto,
                                Principal principal) {

        // Step 1: Get the username of the logged-in user securely from the Principal object.
        String username = principal.getName();

        // Step 2: Delegate the business logic of saving the comment to the CommentService.
        commentService.saveComment(postId, username, commentDto);

        // Step 3: Redirect the user back to the post detail page using the PRG pattern.
        // This prevents duplicate form submissions on page refresh.
        return "redirect:/posts/" + postId;
    }
}