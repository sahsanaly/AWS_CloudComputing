package com.aws_assignment_1.createMusic.functions.subscription;

public class SubscriptionRequestByMusicTitle {

    private String musicTitle;

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    @Override
    public String toString() {
        return "SubscriptionRequestByMusicTitle [musicTitle=" + musicTitle + "]";
    }
}
