package com.aws_assignment_1.createMusic.functions.subscription;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.Subscription;

public class GetAllSubscribedMusic implements RequestHandler<Object, Object> {


    @Override
    public SubscriptionResponse handleRequest(Object input, Context context) {

        System.out.println("In lambda function");
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("subscription");

        ScanResult scanResult = builder.build().scan(scanRequest);
        SubscriptionResponse response = new SubscriptionResponse();
        if(scanResult.getCount()>0) {
            for(int i = 0; i <scanResult.getCount(); i++) {
                Subscription subscription = new Subscription();
                subscription.setUser_name(scanResult.getItems().get(i).get("user_name").getS());
                subscription.setMusicTitle(scanResult.getItems().get(i).get("musicTitle").getS());
                response.getSubs().add(subscription);
            }
            return response;
        }else {
            return null;
        }
    }
}
