package com.aws_assignment_1.createMusic.functions.subscription;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.Subscription;

public class RemoveSubscriptionFunction implements RequestHandler<RemoveSubscriptionRequest, Context> {
    @Override
    public Context handleRequest(RemoveSubscriptionRequest input, Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1 );
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("subscription");
        if (input.getUser_name() == null) {
            throw new IllegalArgumentException(input.toString());
        }
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey("musicTitle", input.getMusicTitle(), "user_name", input.getUser_name());
        table.deleteItem(deleteItemSpec);
        return null;
    }
}
