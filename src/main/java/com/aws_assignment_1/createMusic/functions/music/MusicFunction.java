package com.aws_assignment_1.createMusic.functions.music;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.Music;

public class MusicFunction implements RequestHandler<Music, Context> {
    @Override
    public Context handleRequest(Music input, Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1 );
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("music");
        if (input.getTitle() == null) {
            throw new IllegalArgumentException(input.toString());
        }
        final Item item = new Item()
                .withString("title", input.getTitle())
                .withString("artist", input.getArtist())
                .withString("year", input.getYear())
                .withString("img_url", input.getImg_url())
                .withString("web_url", input.getWeb_url());
        table.putItem(item);
        return null;
    }
}