package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
	@Autowired
    private UserRepository userRepository;
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

	@PostMapping("/register")
    public String processRegistrationForm(User user, Model model) {
        userRepository.save(user);
        model.addAttribute("message", "Registration successful!");

        // Retrieve the latest registered user
        User latestUser = userRepository.findFirstByOrderByRegistrationDateDesc();
        model.addAttribute("latestUser", latestUser);

        // Retrieve all registered users
        List<User> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);

        return "success";
    }
}
