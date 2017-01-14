package com.company.app;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.HashMap;
import java.util.Map;

public interface ServiceClient {
    @GET("topics")
    Call<Map<Integer, Map<String, Object>>> getTopics();

    @POST("topics")
    Call<Map<String, Object>> createTopic(@Body HashMap<String, Object> topicRequest);
}
