package com.aws_assignment_1.createMusic.functions.music;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.Music;

import java.util.HashMap;

public class GetMusicByYear implements RequestHandler<MusicRequestByYear, MusicResponse> {
    @Override
    public MusicResponse handleRequest(MusicRequestByYear input, Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1); // specify the region you created the table in.
        final DynamoDB dynamoDB = new DynamoDB(client);

        final Table table = dynamoDB.getTable("music");
        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#yr", "year");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":yyyy", input.getYear());

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("#yr = :yyyy")
                .withNameMap(nameMap)
                .withValueMap(valueMap);
        final ItemCollection<QueryOutcome> items = table.query(spec);

        final MusicResponse response = new MusicResponse();
        for (final Item item : items) {
            final Music music = new Music();
            music.setTitle(item.getString("title"));
            music.setArtist(item.getString("artist"));
            music.setYear(item.getString("year"));
            music.setImg_url(item.getString("img_url"));
            music.setWeb_url(item.getString("web_url"));
            response.getMusic().add(music);
        }
        return response;
    }
}