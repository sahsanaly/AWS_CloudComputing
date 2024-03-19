package com.aws_assignment_1.createMusic.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;

public class UploadToS3 {
    public static void main(String[] args) throws IOException {

        String bucketName = "sahsanalis3bucket";
        String folderName = "musicians/";

        AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();

        JsonParser parser = new JsonFactory().createParser(new File("/Users/ahsanali/Desktop/AWS_Assignment_1/a1.json"));

        JsonNode songsNode = new ObjectMapper().readTree(parser);
        JsonNode rootNode = songsNode.path("songs");
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();
            try{
            String img_url = currentNode.path("img_url").asText();
            String fileName = img_url.substring(img_url.lastIndexOf("/") + 1);
            downloadImage(img_url, fileName);
            File file = new File(fileName);
            String key = folderName + file.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName, key, file);
            PutObjectResult result = client.putObject(request);
            System.out.println("Uploaded " + fileName + " with ETag " + result.getETag() + " to S3 bucket " + bucketName);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void downloadImage(String img_url, String fileName) {
        try {
            URL url = new URL(img_url);
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
