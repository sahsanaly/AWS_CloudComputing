package com.aws_assignment_1.createMusic.functions.user;

public class UserRequestByEmail {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRequestByEmail [email=" + email + "]";
    }

}