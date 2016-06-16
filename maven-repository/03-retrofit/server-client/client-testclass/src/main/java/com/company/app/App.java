package com.company.app;

import retrofit.*;

import java.io.IOException;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ServiceClient serviceClient = retrofit.create(ServiceClient.class);

        TestTopic testTopic = new TestTopic();
        testTopic.setId(2);
        testTopic.setName("testTopic-2");

        Call<TestTopic> builtTopic = serviceClient.createTopic(testTopic);
        Response<TestTopic> builtTopicResponse = builtTopic.execute();
        System.out.println(builtTopicResponse.body());

        Call<Map<Integer, TestTopic>> topics = serviceClient.getTopics();
        Response<Map<Integer, TestTopic>> getTopicsResponse = topics.execute();
        System.out.println(getTopicsResponse.body());
    }
}
