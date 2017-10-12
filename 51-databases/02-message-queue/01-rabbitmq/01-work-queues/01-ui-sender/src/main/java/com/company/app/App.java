package com.company.app;

import com.company.app.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/*

1, create user
  User user = new User();
  user.setBirthDate(new Date());

2, serialize user into json
  String json = serialize(mapper, user);

3, put user json into queue
  channel.basicPublish("", TASK_QUEUE_NAME, null, json.getBytes());
 */
public class App {
  private final static String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("tom");
    user.setCreatedDate(new Date());
    user.setEmailAddress("tom168@yahoo.com");
    user.setFirstName("Tom");
    user.setLastName("Lee");
    user.setLastUpdatedBy("tom");
    user.setLastUpdatedDate(new Date());

    ObjectMapper mapper = new ObjectMapper();
    String json = serialize(mapper, user);

    channel.basicPublish("", TASK_QUEUE_NAME, null, json.getBytes());
    System.out.println(" [x] Sent '" + json + "'");

    channel.close();
    connection.close();
  }

  private static String serialize(ObjectMapper mapper, User user) throws JsonProcessingException {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String jsonStr = mapper.writeValueAsString(user);
    return jsonStr;
  }
}
/*
 [x] Sent '{
  "userId" : null,
  "firstName" : "Tom",
  "lastName" : "Lee",
  "birthDate" : 1507786183497,
  "emailAddress" : "tom168@yahoo.com",
  "lastUpdatedDate" : 1507786183497,
  "lastUpdatedBy" : "tom",
  "createdDate" : 1507786183497,
  "createdBy" : "tom"
}'
 */
