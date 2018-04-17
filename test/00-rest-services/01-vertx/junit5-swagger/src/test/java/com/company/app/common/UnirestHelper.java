package com.company.app.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpStatus;

import java.util.Map;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;

public class UnirestHelper {
  private static ObjectMapper mapper = GlobalMapper.INSTANCE.mapper();

  public static JsonNode get(String url) {
    try {
      HttpResponse<String> response = Unirest.get(url)
          .header("Content-Type", "application/json")
          .asString();

      if(response.getStatus() != HttpStatus.SC_OK) {
        throw new RuntimeException("Failed: response statusCode != " + HttpStatus.SC_OK + "\n" + response.getBody());
      }

      return mapper.readTree(response.getBody());
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static JsonNode post(String url, Map<String, String> headers, JsonNode requestBody, int statusCode) {
    try {
      HttpResponse<String> response = Unirest.post(url)
          .header("Content-Type", "application/json")
          .headers(headers)
          .body(isNull(requestBody) ? "" : requestBody.toString())
          .asString();

      if(response.getStatus() != statusCode) {
        throw new RuntimeException("Failed: response statusCode != " + statusCode + "\n" + response.getBody());
      }

      return mapper.readTree(response.getBody());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static JsonNode put(String url, Map<String, String> headers, JsonNode requestBody) {
    try {
      HttpResponse<String> response = Unirest.put(url)
          .header("Content-Type", "application/json")
          .headers(headers)
          .body(requestBody.toString())
          .asString();

      if(response.getStatus() != HttpStatus.SC_OK) {
        throw new RuntimeException("Failed: response statusCode != " + HttpStatus.SC_OK + "\n" + response.getBody());
      }

      return mapper.readTree(response.getBody());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String delete(String url, String version) {
    try {
      HttpResponse<String> response = Unirest.delete(url)
          .header("Content-Type", "application/json")
          .header("etag", version)
          .asString();

      if(response.getStatus() != HttpStatus.SC_NO_CONTENT) {
        throw new RuntimeException("Failed: response statusCode != " + HttpStatus.SC_NO_CONTENT + "\n" + response.getBody());
      }

      return response.getBody();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
