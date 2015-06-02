package com.example.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class App
{
    public static void main( String[] args )
    {
        Client client = ClientBuilder.newClient();
        String location = "http://localhost:8080/hello-world";
        String result = client.target(location).request().get(String.class);
        System.out.println(result);
    }
}
