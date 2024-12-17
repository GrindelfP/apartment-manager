package to.grindelf.apartmentmanager.application.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import to.grindelf.apartmentmanager.application.spring.services.AuthService;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Show Sign-Up Form
    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup"; // return the name of the HTML template
    }

    // Handle Sign-Up Post Request
    @PostMapping("/signup")
    public String signUp(@RequestParam String name, @RequestParam String password, Model model) {
        try {
            authService.signUp(name, password);
            model.addAttribute("message", "User registered successfully!");
            return "login"; // Redirect to login page
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", "User already exists");
            return "signup";
        }
    }

    // Show Login Form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // return the name of the HTML template
    }

    // Handle Log-In Post Request
    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, Model model) {
        try {
            authService.logIn(name, password);
            model.addAttribute("message", "Welcome, " + name + "!");
            return "home"; // Redirect to home page after successful login
        } catch (NoSuchUserException e) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
