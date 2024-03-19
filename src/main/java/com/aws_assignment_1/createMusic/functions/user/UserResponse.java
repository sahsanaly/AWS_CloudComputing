package com.aws_assignment_1.createMusic.functions.user;

import com.aws_assignment_1.createMusic.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    private List<User> users = new ArrayList<>();

    public List<User> getUser() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

}