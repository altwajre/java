package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

class WhiskyClient {
  private Client client;

  public WhiskyClient() {
    client = ClientBuilder.newClient();
  }

  public JsonNode create(JsonNode whisky) {
    WebTarget target = this.client.target("http://localhost:8080/api/whiskies");
    JsonNode response = target
        .request()
        .post(Entity.entity(whisky, MediaType.APPLICATION_JSON), JsonNode.class);
    System.out.println(response);
    return response;
  }

  public JsonNode update(JsonNode whisky) {
    String id = whisky.get("id").textValue();
    WebTarget target = this.client.target("http://localhost:8080/api/whiskies/" + id);
    JsonNode response = target
        .request()
        .put(Entity.entity(whisky, MediaType.APPLICATION_JSON), JsonNode.class);
    System.out.println(response);
    return response;
  }

  public void delete(String id) {
    WebTarget target = this.client.target("http://localhost:8080/api/whiskies/" + id);
    String response = target
        .request()
        .delete(String.class);
    System.out.println(response);
  }

  public JsonNode getOne(String id) {
    WebTarget target = this.client.target("http://localhost:8080/api/whiskies/" + id);
    JsonNode response = target
        .request()
        .get(JsonNode.class);
    System.out.println(response);
    return response;
  }

  public JsonNode getAll() {
    WebTarget target = this.client.target("http://localhost:8080/api/whiskies");
    JsonNode response = target
        .request()
        .get(JsonNode.class);
    System.out.println(response);
    return response;
  }
}

public class App {
  public static void main(String[] args) {
    WhiskyClient whiskyClient = new WhiskyClient();

    whiskyClient.getAll();

    // Create
    ObjectMapper mapper = new ObjectMapper();
    JsonNode createWhisky = mapper.createObjectNode();
    ((ObjectNode) createWhisky).put("name", "Jersey Create");
    ((ObjectNode) createWhisky).put("origin", "UK");

    JsonNode createResponse = whiskyClient.create(createWhisky);
    String id = createResponse.get("id").toString();

    whiskyClient.getAll();

    // Update
    JsonNode updateWhisky = mapper.createObjectNode();
    ((ObjectNode) updateWhisky).put("id", id);
    ((ObjectNode) updateWhisky).put("name", "Jersey Update");
    ((ObjectNode) updateWhisky).put("origin", "UK");

    whiskyClient.update(updateWhisky);

    whiskyClient.getAll();

    // Delete
    whiskyClient.delete(id);

    // Get One
    whiskyClient.getOne("0");
    whiskyClient.getAll();

  }
}
/*
output:
[{"id":0,"name":"Bowmore 15 Years Laimrig","origin":"Scotland, Islay"},{"id":1,"name":"Talisker 57° North","origin":"Scotland, Island"}]
{"id":2,"name":"Jersey Create","origin":"UK"}
[{"id":0,"name":"Bowmore 15 Years Laimrig","origin":"Scotland, Islay"},{"id":1,"name":"Talisker 57° North","origin":"Scotland, Island"},{"id":2,"name":"Jersey Create","origin":"UK"}]
{"id":2,"name":"Jersey Update","origin":"UK"}
[{"id":0,"name":"Bowmore 15 Years Laimrig","origin":"Scotland, Islay"},{"id":1,"name":"Talisker 57° North","origin":"Scotland, Island"},{"id":2,"name":"Jersey Update","origin":"UK"}]

{"id":0,"name":"Bowmore 15 Years Laimrig","origin":"Scotland, Islay"}
[{"id":0,"name":"Bowmore 15 Years Laimrig","origin":"Scotland, Islay"},{"id":1,"name":"Talisker 57° North","origin":"Scotland, Island"}]
 */
