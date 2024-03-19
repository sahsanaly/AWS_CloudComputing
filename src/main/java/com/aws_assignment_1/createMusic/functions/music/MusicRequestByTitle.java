package com.aws_assignment_1.createMusic.functions.music;

public class MusicRequestByTitle {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MusicRequestByTitle [title=" + title + "]";
    }
}
