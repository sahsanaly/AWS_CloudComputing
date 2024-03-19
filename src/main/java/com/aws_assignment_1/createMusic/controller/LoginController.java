package com.aws_assignment_1.createMusic.controller;

import com.aws_assignment_1.createMusic.functions.AmazonClient;
import com.aws_assignment_1.createMusic.model.User;
import com.aws_assignment_1.createMusic.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@SessionAttributes({"email", "user_name"})
public class LoginController {
    @Autowired
    private LoginService loginService;
    
    @Autowired
    AmazonClient amazonClient;

    @Autowired
    SubscriptionController subscriptionController;

    @Autowired
    public Optional<User> user;


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String validLogin(@RequestParam String email, @RequestParam String password, ModelMap model) throws IOException {
        if (loginService.isValidCredentials(email, password)) {

            user =  amazonClient.findByEmail(email);

            model.put("email", email);
            model.put("password", password);
            model.put("user_name", user.get().getUser_name());

            //subscriptionController.displayMusic(user, model);
            return "redirect:/mainPage";
        } else {
            model.put("errorMessage", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String username, @RequestParam String password, ModelMap model) throws IOException {
        boolean uniqueEmail = loginService.isValidEmail(email);

        if(!uniqueEmail) {
            model.put("emailErrorMessage", "Email already taken");
        }
        else {
            User user = new User();
            user.setEmail(email);
            user.setUser_name(username);
            user.setPassword(password);

            loginService.registerUser(user);
            return "redirect:/login";
        }
        return "register";

    }
}

