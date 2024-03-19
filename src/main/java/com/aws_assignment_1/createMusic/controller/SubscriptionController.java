package com.aws_assignment_1.createMusic.controller;

import com.aws_assignment_1.createMusic.functions.AmazonClient;
import com.aws_assignment_1.createMusic.model.Music;
import com.aws_assignment_1.createMusic.model.Subscription;
import com.aws_assignment_1.createMusic.model.User;
import com.aws_assignment_1.createMusic.service.SubscriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"email", "user_name", "queryMusic", "subscribedMusic", "queryErrorMessage"})
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    public Optional<User> user;

    @GetMapping("/mainPage")
    public String showMainPage(ModelMap model) {
        user.get().setUser_name(model.get("user_name").toString());
        List<Music> musicList = subscriptionService.retrieveMusicByUsername(user.get().getUser_name());
        if(musicList == null){
            model.put("errorMessage", "No music is subscribed!");
        }else{
            model.put("subscribedMusic", musicList);
        }
        return "/mainPage";

    }

    @PostMapping ("/mainPage")
    public String displayMusic(Optional<User> user, ModelMap model){
        this.user = user;
        return "/mainPage";


    }


//
    @GetMapping("/subscribe")
    public String addMusic(@RequestParam("title") String title, ModelMap model){
        boolean notSubscribedAlready = subscriptionService.isValidMusic(user.get().getUser_name(), title);
        if (!notSubscribedAlready){
            model.put("MusicAlreadySubMessage", "Music already subscribed!");

        }
        else{
            Subscription subscription = new Subscription(user.get().getUser_name(), title);
            subscriptionService.addMusic(subscription);
        }
        return "redirect:/mainPage";
    }
//
    @GetMapping("/remove")
    public String removeMusic(@RequestParam String title, ModelMap model) throws JsonProcessingException {
        subscriptionService.removeMusic(title, this.user.get().getUser_name());
        ModelMap newModel = new ModelMap();
        return "redirect:/mainPage";
    }

    @PostMapping("/query")
    public String queryMusic(@RequestParam String title, @RequestParam String artist, @RequestParam String year,
                             ModelMap model){
        if (title.equals("")){
            title=null;
        }
        if (artist.equals("")){
            artist=null;
        }
        if (year.equals("")){
            year=null;
        }
        List<Music> music = subscriptionService.queryMusic(title, year, artist);
        System.out.println("Printing music: "+music);
        if(music==null){
            model.put("queryErrorMessage", "No music is available!");
            System.out.println("Query Error");
        }else{
            model.put("queryMusic", music);
            System.out.println("Query music: " + music );
        }
        return "redirect:/mainPage";
    }


}
