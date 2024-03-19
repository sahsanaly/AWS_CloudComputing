package com.aws_assignment_1.createMusic.functions.music;

public class MusicRequestByYear {
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "MusicRequestByYear [year=" + year + "]";
    }
}
