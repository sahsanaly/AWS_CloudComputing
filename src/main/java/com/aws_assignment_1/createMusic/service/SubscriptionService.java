package com.aws_assignment_1.createMusic.service;

import com.aws_assignment_1.createMusic.functions.AmazonClient;
import com.aws_assignment_1.createMusic.model.Music;
import com.aws_assignment_1.createMusic.model.Subscription;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SubscriptionService {

    @Autowired
    AmazonClient amazonClient;

    public List<Music> retrieveMusicByUsername(String user_name){

        List<Music> musicList = new ArrayList<>();
        List<Subscription> subMusic = new ArrayList<>();
        List<Subscription> allSub = amazonClient.getAllSubscribedMusic(user_name);
        if(allSub!=null) {
            for (int i = 0; i < allSub.size(); i++) {
                if (user_name.equals(allSub.get(i).getUser_name())) {
                    subMusic.add(allSub.get(i));
                }
            }
        }
        if (subMusic != null) {
            for (int i = 0; i < subMusic.size(); i++){
                System.out.println(subMusic.get(i));
                List<Music> allMusic = amazonClient.getAllMusic();
                if(allMusic!=null) {
                    for (int j = 0; j < allMusic.size(); j++) {
                        if (subMusic.get(i).getMusicTitle().toLowerCase().equals(allMusic.get(j).getTitle().toLowerCase())){
                            musicList.add(allMusic.get(j));
                        }
                    }
                }
            }
        }
        return musicList;
    }
    public List<Music> queryMusic(String musicTitle, String musicYear, String musicArtist) {
        List<Music> returnMusic = new ArrayList<>();
        List<Music> allMusic = amazonClient.getAllMusic();
        if (musicTitle != null && musicYear != null && musicArtist != null) {
            for (int i = 0; i < allMusic.size(); i++) {
                if (musicTitle.toLowerCase().equals(allMusic.get(i).getTitle().toLowerCase()) && musicYear.toLowerCase().equals(allMusic.get(i).getYear().toLowerCase())
                        && musicArtist.toLowerCase().equals(allMusic.get(i).getArtist().toLowerCase())) {
                    returnMusic.add(allMusic.get(i));
                }
            }
        } else if (musicTitle == null && musicYear != null && musicArtist != null) {
            for (int i = 0; i < allMusic.size(); i++) {
                if (musicYear.toLowerCase().equals(allMusic.get(i).getYear().toLowerCase()) && musicArtist.toLowerCase().equals(allMusic.get(i).getArtist().toLowerCase())) {
                    returnMusic.add(allMusic.get(i));
                }
            }
        } else if (musicTitle != null && musicYear == null && musicArtist != null) {

            for (int i = 0; i < allMusic.size(); i++) {
                if (musicTitle.toLowerCase().equals(allMusic.get(i).getTitle().toLowerCase()) && musicArtist.toLowerCase().equals(allMusic.get(i).getArtist().toLowerCase())) {
                    returnMusic.add(allMusic.get(i));
                }
            }
        } else if (musicTitle != null && musicYear != null && musicArtist == null) {

            for (int i = 0; i < allMusic.size(); i++) {
                if (musicTitle.toLowerCase().equals(allMusic.get(i).getTitle().toLowerCase()) && musicYear.toLowerCase().equals(allMusic.get(i).getYear().toLowerCase())) {
                    returnMusic.add(allMusic.get(i));
                }
            }
        } else if (musicTitle != null && musicYear == null && musicArtist == null) {

            for (int i = 0; i < allMusic.size(); i++) {


                if (musicTitle.toLowerCase().equals(allMusic.get(i).getTitle().toLowerCase())) {

                    returnMusic.add(allMusic.get(i));

                }
            }
        } else if (musicTitle == null && musicYear == null && musicArtist != null) {

            for (int i = 0; i < allMusic.size(); i++) {
                if (musicArtist.toLowerCase().equals(allMusic.get(i).getArtist().toLowerCase())) {
                    returnMusic.add(allMusic.get(i));
                }
            }
        } else if (musicTitle == null && musicYear != null && musicArtist == null) {
            for (int i = 0; i < allMusic.size(); i++) {
                if (musicYear.toLowerCase().equals(allMusic.get(i).getYear().toLowerCase())) {
                    returnMusic.add(allMusic.get(i));
                }
            }
        }
        else{
            returnMusic = null;
        }

        return returnMusic;
    }


    public Boolean isValidMusic(String user_name, String title) {
        List<Subscription> allSub = amazonClient.getAllSubscribedMusic(user_name);
        if(allSub!=null) {
            for (int i = 0; i < allSub.size(); i++) {
                if(title.equals(allSub.get(i).getMusicTitle())){
                    if(user_name.equals(allSub.get(i).getUser_name())){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void addMusic(Subscription subscription) {
        amazonClient.addMusic(subscription);
    }

    public void removeMusic(String title, String user_name) throws JsonProcessingException {
        amazonClient.removeMusic(title, user_name);
    }
}

