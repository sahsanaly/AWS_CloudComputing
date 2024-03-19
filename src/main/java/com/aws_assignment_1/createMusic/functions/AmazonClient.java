package com.aws_assignment_1.createMusic.functions;

import com.aws_assignment_1.createMusic.functions.music.MusicResponse;
import com.aws_assignment_1.createMusic.functions.subscription.SubscriptionResponse;
import com.aws_assignment_1.createMusic.functions.user.UserResponse;
import com.aws_assignment_1.createMusic.model.Music;
import com.aws_assignment_1.createMusic.model.Subscription;
import com.aws_assignment_1.createMusic.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;



@Service
public class AmazonClient {

    @Autowired
    private RestTemplate restTemplate;

    public Optional<User> findByUsername(String user_name) {

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/user/findByUsername/"+user_name);

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        UserResponse users = restTemplate.getForObject(uriComponents.toUri(), UserResponse.class);

        if(!users.getUser().isEmpty()) {
            return Optional.of(users.getUser().get(0));
        }else {
            return null;
        }
    }

    public Optional<User> findByEmail(String email) throws IOException {

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/user/findByEmail/"+email);

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        UserResponse users = restTemplate.getForObject(uriComponents.toUri(), UserResponse.class);


        if(!users.getUser().isEmpty()) {
            return Optional.of(users.getUser().get(0));
        }else {
            return null;
        }
    }

    public void registerUser(User user) throws JsonProcessingException {

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/register");

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("email", user.getEmail())
                .put("user_name", user.getUser_name())
                .put("password", user.getPassword());
        String jsonPayload = objectMapper.writeValueAsString(jsonNode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toUri(), request, String.class);

    }


    public Music findMusicByTitle(String title) {
        String pushUrl = "https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/music/findByMusicTitle/" + title;
        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(pushUrl);

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        MusicResponse musicResponse = restTemplate.getForObject(uriComponents.toUri(), MusicResponse.class);

        System.out.println(musicResponse.getMusic());

        if(!musicResponse.getMusic().isEmpty()) {
            return musicResponse.getMusic().get(0);
        }else {
            return null;
        }
    }

    public Music findMusicByYear(String musicYear) {
        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/music/findByMusicYear/"+musicYear);

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        MusicResponse musicResponse = restTemplate.getForObject(uriComponents.toUri(), MusicResponse.class);

        if(!musicResponse.getMusic().isEmpty()) {
            return musicResponse.getMusic().get(0);
        }else {
            return null;
        }
    }

    public Music findMusicByArtist(String musicArtist) {
        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/music/findByMusicArtist/"+musicArtist);

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        MusicResponse musicResponse = restTemplate.getForObject(uriComponents.toUri(), MusicResponse.class);

        if(!musicResponse.getMusic().isEmpty()) {
            return musicResponse.getMusic().get(0);
        }else {
            return null;
        }
    }

    public List<Subscription> findSubscriptionByUsername(String user_name) {

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/subscription/findSubscriptionByUsername/"+user_name);

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        SubscriptionResponse subs = restTemplate.getForObject(uriComponents.toUri(), SubscriptionResponse.class);

        if(subs.getSubs().size() != 0) {
            return subs.getSubs();
        }else {
            return null;
        }
    }

    public void addMusic(Subscription subscription) {

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/subscription/addMusic");

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("user_name", subscription.getUser_name());
        map.add("musicTitle", subscription.getMusicTitle());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toUri(), request, String.class);

    }

    public void removeMusic(String musicTitle, String user_name) throws JsonProcessingException {
        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/subscription/removeMusic");

        // encode the complete url properly because we pass the subscription information as query parameters
        UriComponents uriComponents = builder.build().encode();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("musicTitle", musicTitle)
                .put("user_name", user_name);
        String jsonPayload = objectMapper.writeValueAsString(jsonNode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toUri(), request, String.class);

    }

    public List<Subscription> getAllSubscribedMusic(String user_name) {

        List<Subscription> returnSubs = new ArrayList<>();

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/music/getAllSubscribedMusic");

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        SubscriptionResponse key = restTemplate.getForObject(uriComponents.toUri(), SubscriptionResponse.class);

//        System.out.println("Subscribed music: " + key.getSubs());

        if(key!=null) {
            for (int i=0; i<key.getSubs().size(); i++){
                returnSubs.add(key.getSubs().get(i));
            }
            return returnSubs;
        }else {
            return null;
        }
    }

    public List<Music> getAllMusic() {

        List<Music> returnMusic = new ArrayList<>();

        // build full url with provided query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://97ktcr808i.execute-api.us-east-1.amazonaws.com/music/music/getAllMusic");

        // encode the complete url properly because we pass book name as one of the query parameter
        UriComponents uriComponents = builder.build().encode();

        // convert json response string into its POJO representation
        MusicResponse key = restTemplate.getForObject(uriComponents.toUri(), MusicResponse.class);

        if(key!=null) {
            for (int i=0; i<key.getMusic().size(); i++){
                returnMusic.add(key.getMusic().get(i));
            }
            return returnMusic;
        }else {
            return null;
        }
    }
}
