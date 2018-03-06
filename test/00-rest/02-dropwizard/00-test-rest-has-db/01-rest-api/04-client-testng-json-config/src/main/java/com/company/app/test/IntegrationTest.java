package com.company.app.test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.app.Config;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class IntegrationTest {

    @Test(priority = 1)
    public void getRequest_test() throws Exception{
        String path = Config.URL + "/contact/1";
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(path)
                .request()
                .get();
        Assert.assertEquals(response.getStatus(), 200);
        response.bufferEntity();
        Map rawJson = response.readEntity(Map.class);
        Assert.assertEquals(rawJson.get("firstName"), "John");
        Assert.assertEquals(rawJson.get("lastName"), "Doe");
        Assert.assertEquals(rawJson.get("phone"), "+123456789");
    }

    @Test(priority = 1)
    // Use POJO for write request
    public void postRequest_pojo_test() throws Exception{
        String path = Config.URL + "/contact";
        Contact contact = new Contact(0, "Tom", "Lee", "+1112223333");
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(path)
                .request()
                .post(Entity.entity(contact, MediaType.APPLICATION_JSON));
        String location = response.getLocation().toString();
        // Verify the added record
        verityRecordByUsingGetRequest(client, location, contact);
    }

    @Test(priority = 1)
    // Use JSONObject for write request
    public void postRequest_jsonobject_test() throws Exception{
        String path = Config.URL + "/contact";
        Client client = ClientBuilder.newClient();
        JSONObject contact = new JSONObject();
        contact.put("id", 0);
        contact.put("firstName", "jo_first");
        contact.put("lastName", "jo_last");
        contact.put("phone", "+4445556666");
        String result = contact.toString();
        System.out.println(result);

        Response response = client
                .target(path)
                .request()
                .post(Entity.entity(contact.toString(), MediaType.APPLICATION_JSON));
        String location = response.getLocation().toString();
        System.out.println(location);

        // Verify the added record
        verityRecordByUsingGetRequest(client, location, new Contact(0, "jo_first", "jo_last", "+4445556666"));
    }

    @Test(priority = 1)
    public void putRequest_test() throws Exception{
        String path = Config.URL + "/contact";

        // POST request - add a new record
        Contact postContact = new Contact(0, "Tom", "Lee", "+1112223333");
        Client client = ClientBuilder.newClient();
        Response postResponse = client
                .target(path)
                .request()
                .post(Entity.entity(postContact, MediaType.APPLICATION_JSON));
        Assert.assertEquals(postResponse.getStatus(), 201);
        String location = postResponse.getLocation().toString();
        // Verify the added record
        verityRecordByUsingGetRequest(client, location, postContact);

        // PUT request - update the record
        Contact putContact = new Contact(0, "UpdatedLast", "UpdatedFirst", "+9998887777");
        Response putResponse = client.target(location).request()
                .put(Entity.entity(putContact, MediaType.APPLICATION_JSON));
        Assert.assertEquals(putResponse.getStatus(), 200);
        verityRecordByUsingGetRequest(client, location, putContact);
    }

    @Test(priority = 1)
    public void deleteRequest_test() throws Exception{
        String path = Config.URL + "/contact";

        // POST request - add a new record
        Contact postContact = new Contact(0, "Tom", "Lee", "+1112223333");
        Client client = ClientBuilder.newClient();
        Response postResponse = client
                .target(path)
                .request()
                .post(Entity.entity(postContact, MediaType.APPLICATION_JSON));
        Assert.assertEquals(postResponse.getStatus(), 201);
        String location = postResponse.getLocation().toString();
        // Verify the added record
        verityRecordByUsingGetRequest(client, location, postContact);

        // DELETE request - delete the record
        Response deleteResponse = client.target(location).request().delete();
        Assert.assertEquals(deleteResponse.getStatus(), 204);

        // GET request - verify the deleted record is gone
        Response getResponse = client
                .target(location)
                .request()
                .get();
        String status = Integer.toString(getResponse.getStatus());
        Assert.assertEquals(getResponse.getStatus(), 200);
        getResponse.bufferEntity();
        Map rawJson = getResponse.readEntity(Map.class);
        Assert.assertEquals(rawJson, null);
    }

    private void verityRecordByUsingGetRequest(Client client, String location, Contact expectedContact) {
        Response response = client
                .target(location)
                .request()
                .get();
        Assert.assertEquals(response.getStatus(), 200);
        response.bufferEntity();
        Map rawJson = response.readEntity(Map.class);
        Assert.assertEquals(rawJson.get("firstName"), expectedContact.getFirstName());
        Assert.assertEquals(rawJson.get("lastName"), expectedContact.getLastName());
        Assert.assertEquals(rawJson.get("phone"), expectedContact.getPhone());
    }
}
