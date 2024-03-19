package com.aws_assignment_1.createMusic.tasks;

import java.io.*;
import java.util.Iterator;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class LoadMusic {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();
        System.out.println("test 1");

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("music");

        System.out.println("test 2");

        JsonParser parser = new JsonFactory().createParser(new File("/Users/ahsanali/Desktop/AWS_Assignment_1/a1.json"));

        System.out.println("test 3");

        System.out.println(parser);

        System.out.println("test 4");

        JsonNode songsNode = new ObjectMapper().readTree(parser);

        JsonNode rootNode = songsNode.path("songs");

        System.out.println(rootNode);

        Iterator<JsonNode> iter = rootNode.iterator();

        System.out.println(iter);

        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();

            System.out.println(currentNode);

            String title = currentNode.path("title").asText();
            String artist = currentNode.path("artist").asText();
            String year = currentNode.path("year").asText();
            String web_url = currentNode.path("web_url").asText();
            String img_url = currentNode.path("img_url").asText();

            try {
                table.putItem(new Item()
                        .withPrimaryKey("title", title, "artist", artist)
                        .withString("year", year)
                        .withString("web_url", web_url)
                        .withString("img_url", img_url));
                System.out.println("PutItem succeeded: " + year + " " + title);

            }
            catch (Exception e) {
                System.err.println("Unable to add movie: " + year + " " + title);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }
}