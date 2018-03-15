package com.dwbook.tests;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class IntegrationTest {
    @Test(priority = 1)
    public void getRequest_test() throws Exception{
        CredentialsProvider credentialsProvider = new org.apache.http.impl.client.BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("will", "pass")
        );

        ClientConfig cc = new ClientConfig();
        cc.property(ApacheClientProperties.CREDENTIALS_PROVIDER, credentialsProvider);
        cc.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newClient(cc);
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
}
