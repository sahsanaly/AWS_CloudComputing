//package com.aws_assignment_1.createMusic.controller;
//
//import com.aws_assignment_1.createMusic.functions.AmazonClient;
//import com.aws_assignment_1.createMusic.model.Music;
//import com.aws_assignment_1.createMusic.model.User;
//import com.aws_assignment_1.createMusic.service.MusicService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping(path="/mainPage")
//public class MusicController {
//
//    @Autowired
//    private MusicService musicService;
//
//    @Autowired
//    AmazonClient amazonClient;
//
//    @Autowired
//    public Optional<User> user;
//
//    @PostMapping("/query")
//    public String queryMusic(@RequestParam String title, @RequestParam String year,
//                               @RequestParam String artist, ModelMap model){
//        System.out.println("Title: "+title);
//        System.out.println("Year: "+year);
//        System.out.println("Artist: "+artist);
//        List<Music> music = musicService.queryMusic(title, year, artist);
//        if(music == null){
//            model.put("errorMessage", "No music is available!");
//        }else{
//            model.put("queryMusic", music);
//        }
//        return "redirect:/mainPage";
//    }
//}
