package com.company.app;

import retrofit.Call;
import retrofit.JacksonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ServiceClient serviceClient = retrofit.create(ServiceClient.class);

        HashMap<String, Object> topicRequest = new HashMap<String, Object>() {{
            put("id", 2);
            put("name", "topic-2");
        }};
        Call<Map<String, Object>> builtTopic = serviceClient.createTopic(topicRequest);
        Response<Map<String, Object>> builtTopicResponse = builtTopic.execute();
        System.out.println(builtTopicResponse.body());

        Call<Map<Integer, Map<String, Object>>> topics = serviceClient.getTopics();
        Response<Map<Integer, Map<String, Object>>> getTopicsResponse = topics.execute();
        System.out.println(getTopicsResponse.body());
    }
}
/*
output:
{id=2, name=topic-2}
Disconnected from the target VM, address: '127.0.0.1:56122', transport: 'socket'
{1={id=1, name=topic-1}, 2={id=2, name=topic-2}}
 */
