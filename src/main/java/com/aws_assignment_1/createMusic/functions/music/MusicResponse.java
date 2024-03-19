package com.aws_assignment_1.createMusic.functions.music;

import com.aws_assignment_1.createMusic.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicResponse {

    private List<Music> music = new ArrayList<>();

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }
}