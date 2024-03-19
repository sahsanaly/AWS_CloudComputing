package com.aws_assignment_1.createMusic.functions.user;

public class UserRequestByUsername {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserRequestByUsername [username=" + username + "]";
    }
}
