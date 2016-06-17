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

        TopicRequest topicRequest = new TopicRequest();
        topicRequest.setId(2);
        topicRequest.setName("topicRequest-2");

        Call<TopicRequest> builtTopic = serviceClient.createTopic(topicRequest);
        Response<TopicRequest> builtTopicResponse = builtTopic.execute();
        System.out.println(builtTopicResponse.body());

        Call<Map<Integer, TopicRequest>> topics = serviceClient.getTopics();
        Response<Map<Integer, TopicRequest>> getTopicsResponse = topics.execute();
        System.out.println(getTopicsResponse.body());
    }
}
/*
output:
TopicRequest{id=2, name=topicRequest-2}
{1=TopicRequest{id=1, name=topic-1}, 2=TopicRequest{id=2, name=topicRequest-2}}
 */
