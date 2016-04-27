package com.company.app.client;

import com.company.app.Topic;
import com.company.app.client.ServiceClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServiceClientApp {
    public static void main( String[] args ) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceClient serviceClient = retrofit.create(ServiceClient.class);
        Call<Topic> contacts = serviceClient.getContacts();
        Response<Topic> getContactsResponse = contacts.execute();
        System.out.println(getContactsResponse.body().getName());
    }
}
