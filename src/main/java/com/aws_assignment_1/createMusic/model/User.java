package com.aws_assignment_1.createMusic.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class User {
    private String email;
    private String user_name;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
