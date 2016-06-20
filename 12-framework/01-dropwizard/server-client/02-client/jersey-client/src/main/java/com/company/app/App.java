package com.company.app;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        Client client = ClientBuilder.newClient();
        String location = "http://localhost:8080/topics";
        Response response = client.target(location).request().get();
        response.bufferEntity();
        Map rawJson = response.readEntity(Map.class);
        System.out.println(rawJson);
    }
}
/*
output:
{1={id=1, name=topic-1}}
 */
