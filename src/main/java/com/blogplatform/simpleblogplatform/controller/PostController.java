package com.blogplatform.simpleblogplatform.controller;

import com.blogplatform.simpleblogplatform.model.Post;
import com.blogplatform.simpleblogplatform.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // NEW: Import the Model interface
import org.springframework.web.bind.annotation.GetMapping; // NEW: Import GetMapping

import java.util.List; // NEW: Import List

/**
 * PostController handles all public-facing web requests related to blog posts.
 * This includes displaying the list of all posts (homepage) and viewing a single post.
 * It follows the Model-View-Controller (MVC) pattern, where this class acts as the Controller.
 */
@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // --- NEW METHOD START ---

    /**
     * Handles GET requests to the root URL ("/") to display the homepage.
     *
     * @param model The Model object, provided by Spring, to pass data to the view.
     * @return The logical name of the view template to be rendered ("home").
     */
    @GetMapping("/")
    public String showHomePage(Model model) {
        // Step 1: Fetch all posts from the business layer (PostService).
        // This call delegates the work of retrieving data to the service,
        // keeping the controller's responsibility focused on web request handling.
        List<Post> allPosts = postService.findAllPosts();

        // Step 2: Add the list of posts to the model.
        // The first argument, "posts", is the key. This is the name we will use
        // in our Thymeleaf template to access the list of posts.
        // The second argument, allPosts, is the value (the data itself).
        model.addAttribute("posts", allPosts);

        // Step 3: Return the view name.
        // Spring's ViewResolver will look for a template named "home.html"
        // in the `src/main/resources/templates/` directory.
        return "home";
    }
    // --- NEW METHOD END ---
}