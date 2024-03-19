package com.aws_assignment_1.createMusic.functions.subscription;

public class SubscriptionRequestByUsername
{
    private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "SubscriptionRequestByUsername [user_name=" + user_name + "]";
    }
}
