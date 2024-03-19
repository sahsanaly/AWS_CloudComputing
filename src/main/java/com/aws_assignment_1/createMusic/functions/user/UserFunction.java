package com.aws_assignment_1.createMusic.functions.user;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.User;

public class UserFunction implements RequestHandler<User, Context> {
    @Override
    public Context handleRequest(User input, Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1 );
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("login");
        if (input.getEmail() == null) {
            throw new IllegalArgumentException(input.toString());
        }
        final Item item = new Item()
                .withPrimaryKey("email", input.getEmail()) // Every item gets a unique id
                .withString("user_name", input.getUser_name())
                .withString("password", input.getPassword());
        table.putItem(item);
        return null;
    }
}
