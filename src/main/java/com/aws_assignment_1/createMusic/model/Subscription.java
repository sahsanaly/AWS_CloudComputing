package com.aws_assignment_1.createMusic.model;

public class Subscription {
    private String user_name;
    private String musicTitle;

    public Subscription(String user_name, String musicTitle) {
        this.user_name = user_name;
        this.musicTitle = musicTitle;
    }

    public Subscription() {

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "user='" + user_name + '\'' +
                ", music='" + musicTitle + '\'' +
                '}';
    }
}
