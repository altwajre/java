package com.company.app.client;

import com.company.app.Topic;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceClient {
    @GET("contact/all")
    Call<Topic> getContacts();
}
