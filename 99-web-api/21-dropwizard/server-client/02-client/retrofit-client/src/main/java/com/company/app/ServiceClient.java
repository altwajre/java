package com.company.app;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.Map;

public interface ServiceClient {
    @GET("topics")
    Call<Map<Integer, TopicRequest>> getTopics();

    @POST("topics")
    Call<TopicRequest> createTopic(@Body TopicRequest topicRequest);
}
