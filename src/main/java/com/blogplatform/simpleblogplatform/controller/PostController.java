package com.blogplatform.simpleblogplatform.controller;

import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // NEW: Import PathVariable
import com.blogplatform.simpleblogplatform.dto.CommentDto; // NEW: Import the DTO


import java.util.List;

/**
 * PostController handles all public-facing web requests related to blog posts.
 * This includes displaying the list of all posts (homepage) and viewing a single post.
 */
@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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
        return "post-detail";
    }
    // --- NEW METHOD END ---
}