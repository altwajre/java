package com.company.app.client;

import com.company.app.server.Topic;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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
