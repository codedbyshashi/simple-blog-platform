package com.blogplatform.simpleblogplatform.controller;

import com.blogplatform.simpleblogplatform.model.User;
import com.blogplatform.simpleblogplatform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AuthController handles web requests related to user authentication,
 * such as displaying the login and registration pages.
 */
@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the user registration form.
     *
     * @param model The Model object to pass data to the view.
     * @return The logical name of the registration view template.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // We add a new, empty User object to the model. This is the object that
        // the form fields will bind to.
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Processes the submission of the registration form.
     *
     * @param user The User object populated with data from the form.
     * @param redirectAttributes Used to add flash attributes for the redirect.
     * @return A redirect instruction to the login page.
     */
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            // Add a success message that will be displayed on the login page after redirect.
            redirectAttributes.addFlashAttribute("registrationSuccess", "Registration successful! Please log in.");
            return "redirect:/login";
        } catch (IllegalStateException e) {
            // If the username already exists, add an error message and return to the form.
            redirectAttributes.addFlashAttribute("registrationError", e.getMessage());
            return "redirect:/register";
        }
    }
}