package com.aws_assignment_1.createMusic.functions.music;

public class MusicRequestByArtist {
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "MusicRequestByArtist [artist=" + artist + "]";
    }
}
