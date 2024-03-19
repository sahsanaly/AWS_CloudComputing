package com.aws_assignment_1.createMusic.functions.subscription;

public class RemoveSubscriptionRequest {

    private String musicTitle;
    private String user_name;

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "RemoveSubscriptionRequest{" +
                "musicTitle='" + musicTitle + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
