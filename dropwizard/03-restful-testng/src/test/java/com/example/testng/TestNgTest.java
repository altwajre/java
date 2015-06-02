package com.example.testng;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Test ()
public class TestNgTest {
    @Test
    public void getRequest_helloWorld_test() throws Exception{
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/hello-world")
                .request()
                .accept("application/json")
                .get();
        try {
            Assert.assertEquals(response.getStatus(), 200);
            response.bufferEntity();
            Map rawJson = response.readEntity(Map.class);
            System.out.println(rawJson);
            System.out.println(rawJson.get("content"));
            Assert.assertEquals(rawJson.get("content"), "Hello yml, Stranger yml!");
        }finally {
            response.close();
        }
    }
}
