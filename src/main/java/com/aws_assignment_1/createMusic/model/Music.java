package com.aws_assignment_1.createMusic.model;

public class Music {
    private String title;
    private String artist;
    private String year;
    private String img_url;
    private String web_url;

    public Music(String title, String artist, String year, String img_url, String web_url) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.img_url = img_url;
        this.web_url = web_url;
    }

    public Music() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", year='" + year + '\'' +
                ", img_url='" + img_url + '\'' +
                ", web_url='" + web_url + '\'' +
                '}';
    }
}
