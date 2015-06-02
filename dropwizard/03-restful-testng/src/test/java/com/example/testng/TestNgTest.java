package com.example.testng;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test ()
public class TestNgTest {
    @Test()
    public void test() {
        Client client = ClientBuilder.newClient();
        String location = "http://localhost:8080/hello-world";
        String result = client.target(location).request().get(String.class);
        System.out.println(result);
        Assert.assertEquals("First", "First");
    }
}
