package com.blogplatform.simpleblogplatform.controller;

import com.blogplatform.simpleblogplatform.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PostController handles all public-facing web requests related to blog posts.
 * This includes displaying the list of all posts (homepage) and viewing a single post.
 * It follows the Model-View-Controller (MVC) pattern, where this class acts as the Controller.
 */
@Controller
public class PostController {

    // The controller needs to talk to the service layer to fetch post data.
    // We use the same best-practice constructor injection you've used before.
    private final PostService postService;

    /**
     * Constructs the PostController with its required dependency, the PostService.
     * Spring's Dependency Injection will automatically provide the PostService bean.
     *
     * @param postService The service responsible for post-related business logic.
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // In the next tasks, we will add handler methods here, such as:
    // - A method annotated with @GetMapping("/") to handle homepage requests.
    // - A method annotated with @GetMapping("/posts/{id}") to handle post detail page requests.
}