package com.aws_assignment_1.createMusic.service;

import com.aws_assignment_1.createMusic.functions.AmazonClient;
import com.aws_assignment_1.createMusic.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class LoginService {

    @Autowired
    AmazonClient amazonClient;

    public boolean isValidCredentials(String email, String password) throws IOException {
        System.out.println(email + ", " + password + "test3");
        Optional<User> user = amazonClient.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            return email.equalsIgnoreCase(user.get().getEmail()) &&
                    password.equalsIgnoreCase(user.get().getPassword());
        }

    }
    public boolean isValidUsername(String user_name) {
        Optional<User> user = amazonClient.findByUsername(user_name);
        if(user == null) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidEmail(String email) throws IOException {
        Optional<User> user = amazonClient.findByEmail(email);
        if(user == null) {
            return true;
        }
        else{
            return false;
        }
    }
    public void registerUser(User user) throws JsonProcessingException {
        amazonClient.registerUser(user);
    }
}

