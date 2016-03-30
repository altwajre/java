package com.dwbook.tests;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class IntegrationTest {

    @Test(priority = 1)
    public void getRequest_test() throws Exception{
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://localhost:8080/contact/1")
                .request()
                .get();
        Assert.assertEquals(response.getStatus(), 200);
        response.bufferEntity();
        Map rawJson = response.readEntity(Map.class);
        System.out.println(rawJson);
        String firstName = rawJson.get("firstName").toString();
        System.out.println(firstName);
        String lastName = rawJson.get("lastName").toString();
        System.out.println(lastName);
    }

    @Test(priority = 1)
    // Use POJO for write request
    public void postRequest_pojo_test() throws Exception{
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://localhost:8080/contact")
                .request()
                .post(Entity.entity(new Contact(0, "Tom", "Lee", "+1112223333"), MediaType.APPLICATION_JSON));
        String location = response.getLocation().toString();
        System.out.println(location);

        // Verify the added record
        verityRecordByUsingGetRequest(client, location);
    }

    @Test(priority = 1)
    // Use JSONObject for write request
    public void postRequest_jsonobject_test() throws Exception{
        Client client = ClientBuilder.newClient();
        JSONObject contact = new JSONObject();
        contact.put("id", 0);
        contact.put("firstName", "jo_first");
        contact.put("lastName", "jo_last");
        contact.put("phone", "4445556666");
        String result = contact.toString();
        System.out.println(result);

        Response response = client
                .target("http://localhost:8080/contact")
                .request()
                .post(Entity.entity(contact.toString(), MediaType.APPLICATION_JSON));
        String location = response.getLocation().toString();
        System.out.println(location);

        // Verify the added record
        verityRecordByUsingGetRequest(client, location);
    }

    @Test(priority = 1)
    public void putRequest_test() throws Exception{
        Client client = ClientBuilder.newClient();
        Response postResponse = client
                .target("http://localhost:8080/contact")
                .request()
                .post(Entity.entity(new Contact(0, "Tom", "Lee", "+1112223333"), MediaType.APPLICATION_JSON));
        Assert.assertEquals(postResponse.getStatus(), 201);
        String location = postResponse.getLocation().toString();
        System.out.println(location);

        // Verify the added record
        verityRecordByUsingGetRequest(client, location);

        Response putResponse = client.target(location).request()
                .put(Entity.entity(new Contact(0, "UpdatedLast", "UpdatedFirst", "+9998887777"), MediaType.APPLICATION_JSON));
        Assert.assertEquals(putResponse.getStatus(), 200);
        verityRecordByUsingGetRequest(client, location);
    }

    @Test(priority = 1)
    public void deleteRequest_test() throws Exception{
        Client client = ClientBuilder.newClient();
        Response postResponse = client
                .target("http://localhost:8080/contact")
                .request()
                .post(Entity.entity(new Contact(0, "Tom", "Lee", "+1112223333"), MediaType.APPLICATION_JSON));
        Assert.assertEquals(postResponse.getStatus(), 201);
        String location = postResponse.getLocation().toString();
        System.out.println(location);

        // Verify the added record
        verityRecordByUsingGetRequest(client, location);

        Response deleteResponse = client.target(location).request().delete();
        Assert.assertEquals(deleteResponse.getStatus(), 204);

        Response getResponse = client
                .target(location)
                .request()
                .get();
        String status = Integer.toString(getResponse.getStatus());
        Assert.assertEquals(getResponse.getStatus(), 200);
        getResponse.bufferEntity();
        Map rawJson = getResponse.readEntity(Map.class);
        System.out.println(rawJson);
        Assert.assertEquals(rawJson, null);
    }

    private void verityRecordByUsingGetRequest(Client client, String location) {
        Response response = client
                .target(location)
                .request()
                .get();
        Assert.assertEquals(response.getStatus(), 200);
        response.bufferEntity();
        Map rawJson = response.readEntity(Map.class);
        System.out.println(rawJson);
        String firstName = rawJson.get("firstName").toString();
        System.out.println(firstName);
    }
}
