package com.company.app;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App
{
    public static void main( String[] args ) throws IOException {
        String path = args[0];
//        String path = "data.xml";
        byte[] readAllBytes = Files.readAllBytes(Paths.get(path));
        String requestData = new String(readAllBytes);

        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/soap/DemoService?wsdl")
                .request()
                .header("soapAction", "getText")
                .post(Entity.entity(requestData, MediaType.TEXT_XML));
        System.out.println(response);
        response.bufferEntity();
        String responseBody = response.readEntity(String.class);
        System.out.println(responseBody);
    }
}
