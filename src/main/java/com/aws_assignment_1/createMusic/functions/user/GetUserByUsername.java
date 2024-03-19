package com.aws_assignment_1.createMusic.functions.user;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws_assignment_1.createMusic.model.User;

import java.util.HashMap;

public class GetUserByUsername implements RequestHandler<UserRequestByUsername, UserResponse>{
    @Override
    public UserResponse handleRequest(UserRequestByUsername input, Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1); // specify the region you created the table in.
        final DynamoDB dynamoDB = new DynamoDB(client);

        final Table table = dynamoDB.getTable("login");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":un", input.getUsername());

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("user_name = :un")
                .withValueMap(valueMap);
        final ItemCollection<QueryOutcome> items = table.query(spec);

        final UserResponse response = new UserResponse();
        for (final Item item : items) {
            final User user = new User();
            user.setEmail(item.getString("email"));
            user.setUser_name(item.getString("user_name"));
            user.setPassword(item.getString("password"));
            response.getUser().add(user);
        }
        return response;
    }
}
