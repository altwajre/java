package com.company.app.client;

import com.company.app.server.Topic;
import retrofit.Call;
import retrofit.http.GET;

public interface ServiceClient {
    @GET("contact/all")
    Call<Topic> getContacts();
}
