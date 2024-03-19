package com.aws_assignment_1.createMusic.functions.subscription;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.Subscription;

import java.util.HashMap;

public class GetSubscriptionByUsername implements RequestHandler<SubscriptionRequestByUsername, SubscriptionResponse> {
    @Override
    public SubscriptionResponse handleRequest(SubscriptionRequestByUsername input, Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1); // specify the region you created the table in.
        final DynamoDB dynamoDB = new DynamoDB(client);

        final Table table = dynamoDB.getTable("subscription");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":usr", input.getUser_name());

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("user_name = :usr")
                .withValueMap(valueMap);

        final ItemCollection<QueryOutcome> items = table.query(spec);

        final SubscriptionResponse response = new SubscriptionResponse();
        for (final Item item : items) {
            final Subscription subscription = new Subscription();
            subscription.setUser_name(item.getString("user_name"));
            subscription.setMusicTitle(item.getString("musicTitle"));
            response.getSubs().add(subscription);
        }
        return response;
    }
}