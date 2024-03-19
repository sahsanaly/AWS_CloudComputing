package com.aws_assignment_1.createMusic.functions.music;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.functions.subscription.SubscriptionResponse;
import com.aws_assignment_1.createMusic.model.Music;
import com.aws_assignment_1.createMusic.model.Subscription;

public class GetAllMusic implements RequestHandler<Object, Object> {


    @Override
    public MusicResponse handleRequest(Object input, Context context) {

        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("music");

        ScanResult scanResult = builder.build().scan(scanRequest);
        MusicResponse response = new MusicResponse();
        if(scanResult.getCount()>0) {
            for(int i = 0; i <scanResult.getCount(); i++) {
                Music music = new Music();
                music.setTitle(scanResult.getItems().get(i).get("title").getS());
                music.setArtist(scanResult.getItems().get(i).get("artist").getS());
                music.setYear(scanResult.getItems().get(i).get("year").getS());
                music.setImg_url(scanResult.getItems().get(i).get("img_url").getS());
                music.setWeb_url(scanResult.getItems().get(i).get("web_url").getS());
                response.getMusic().add(music);
            }
            return response;
        }else {
            return null;
        }
    }
}
