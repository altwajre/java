package com.company.app.implementations;

import com.company.app.contracts.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpStatus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class JerseyRestClient implements RestClient {
  private Client client = ClientBuilder.newClient();

  @Override
  public JsonNode get(String url) {
    Response response = client.target(url)
        .request()
        .get();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);

    response.bufferEntity();
    JsonNode jsonNode = response.readEntity(JsonNode.class);
    return jsonNode;
  }

  @Override
  public JsonNode post(String url, JsonNode requestBody) {
    Invocation.Builder request = client.target(url)
        .request();

    Response response = request
        .post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));

    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_CREATED);

    response.bufferEntity();
    JsonNode jsonNode = response.readEntity(JsonNode.class);
    return jsonNode;
  }

  @Override
  public JsonNode put(String url, JsonNode requestBody) {
    Invocation.Builder request = client.target(url)
        .request();

    Response response = request
        .put(Entity.entity(requestBody, MediaType.APPLICATION_JSON));

    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);

    response.bufferEntity();
    JsonNode jsonNode = response.readEntity(JsonNode.class);
    return jsonNode;
  }

  @Override
  public String delete(String url) {
    Invocation.Builder request = client.target(url)
        .request();

    Response response = request.delete();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_NO_CONTENT);

    response.bufferEntity();
    return response.readEntity(String.class);
  }
}
