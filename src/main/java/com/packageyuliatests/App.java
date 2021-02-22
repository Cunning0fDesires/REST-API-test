package com.packageyuliatests;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class App {
    public static <JSONArray, JSONObject> void main(String[] args) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/users/1")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(json, Map.class);
            System.out.printf("User number %d is called %s and their email address is %s",
                    map.get("id"), map.get("name"), map.get("email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
         request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/comments?postId=59")
                .build();
        try {
            Response response1 = client.newCall(request).execute();
            String json1 = response1.body().string();
            ObjectMapper mapper1 = new ObjectMapper();
            JsonNode map1 = mapper1.readTree(json1);
            List<JsonNode> emails = map1.findValues("email");
            List<JsonNode> bodies = map1.findValues("body");
          System.out.printf("\n\nPost %s attracted %d comments.  The first three commenters were %s, %s and %s. \n" +
                           "Their messages were:\n %s \n %s \n %s \n",
                  map1.findValue("postId").toString(), map1.size(),
                  emails.get(0).toString(), emails.get(1).toString(), emails.get(2).toString(),
                  bodies.get(0).toString(), bodies.get(1).toString(), bodies.get(2).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonPost = "{\n" +
                "    \"name\": \"Yulia\",\n" +
                "    \"username\": \"cunningofdesires\",\n" +
                "    \"email\": \"111@mail.com\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Somewhere\",\n" +
                "      \"suite\": \"In\",\n" +
                "      \"city\": \"Kharkiv\",\n" +
                "      \"zipcode\": \"99999\"\n" +
                "}\n" +
                "\n" +
                "}";
        RequestBody body = RequestBody.create(jsonPost, MediaType.parse("application/json"));
        request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/users")
                .post(body)
                .build();
        Call call3 = client.newCall(request);
        try {
            Response response3 = call3.execute();
            String json = response3.body().string();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(json, Map.class);
            System.out.printf("\nThe new user is %s, their username is %s, and their email is %s",
                           map.get("name"), map.get("username"), map.get("email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonPost2 = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"title\": \"My title\",\n" +
                "    \"body\": \"This is what I want to say\"\n" +
                "  }";
        body = RequestBody.create(jsonPost2, MediaType.parse("application/json"));
        request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .post(body)
                .build();
        Call call4 = client.newCall(request);
        try {
            Response response3 = call4.execute();
            String json = response3.body().string();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(json, Map.class);
            System.out.printf("\n\nThere is a new post #%d. Its title is %s. The user wrote: %s",
                    map.get("id"), map.get("title"), map.get("body"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

