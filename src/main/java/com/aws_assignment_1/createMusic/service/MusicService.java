//package com.aws_assignment_1.createMusic.service;
//
//import com.aws_assignment_1.createMusic.functions.AmazonClient;
//import com.aws_assignment_1.createMusic.model.Music;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MusicService {
//
//    AmazonClient amazonClient;
//
//    public List<Music> queryMusic(String musicTitle, String musicYear, String musicArtist) {
//        List<Music> returnMusic = null;
//        List<Music> allMusic = amazonClient.getAllMusic();
//        if (musicTitle != null && musicYear != null && musicArtist != null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicTitle == allMusic.get(i).getTitle() && musicYear == allMusic.get(i).getYear()
//                        && musicArtist == allMusic.get(i).getArtist()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        } else if (musicTitle == null && musicYear != null && musicArtist != null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicYear == allMusic.get(i).getYear() && musicArtist == allMusic.get(i).getArtist()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        } else if (musicTitle != null && musicYear == null && musicArtist != null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicTitle == allMusic.get(i).getTitle() && musicArtist == allMusic.get(i).getArtist()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        } else if (musicTitle != null && musicYear != null && musicArtist == null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicTitle == allMusic.get(i).getTitle() && musicYear == allMusic.get(i).getYear()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        } else if (musicTitle != null && musicYear == null && musicArtist == null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicTitle == allMusic.get(i).getTitle()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        } else if (musicTitle == null && musicYear == null && musicArtist != null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicArtist == allMusic.get(i).getArtist()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        } else if (musicTitle == null && musicYear != null && musicArtist == null) {
//            for (int i = 0; i < allMusic.size(); i++) {
//                if (musicYear == allMusic.get(i).getYear()) {
//                    returnMusic.add(allMusic.get(i));
//                }
//            }
//        }
//
//        return returnMusic;
//    }
//
//
//            //title!=null
////            if (music!=null) {
////                if (musicYear != null) {
////                    if (music.getYear() == musicYear) {
////                        if (musicArtist != null) {
////                            if (music.getArtist() == musicArtist) {
////                                returnMusic = music;
////                            }
////                        } else {
////                            returnMusic = music;
////                        }
////                    }
////                } else if (musicArtist != null) {
////                    if (music.getArtist() == musicArtist) {
////                        returnMusic = music;
////                    }
////                } else {
////                    returnMusic = music;
////                }
////            }
////        }
////        else if (musicYear != null){
////            Music music = amazonClient.findMusicByYear(musicYear);
////            if (music!=null) {
////                if (musicArtist != null) {
////                    if (music.getArtist() == musicArtist) {
////                        returnMusic = music;
////                    }
////                }
////                else{
////                    returnMusic=music;
////                }
////            }
////        }else if(musicArtist != null){
////            Music music = amazonClient.findMusicByArtist(musicArtist);
////            if (music!=null){
////                returnMusic=music;
////            }
////        }
////        return returnMusic;
////    }
//}
