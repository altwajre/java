package com.company.app;

import com.company.app.dao.UserJpaDao;
import com.company.app.dao.interfaces.UserDao;
import com.company.app.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*

1, pick up user json from queue
  final Consumer consumer = new DefaultConsumer(channel) {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
        throws IOException {
      String json = new String(body, "UTF-8");

2, deserialize user
  User deserializeUser = deserialize(mapper, json);

3, persist user to database
  persist(deserializeUser);

 */
public class App {
  private final static String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] args) throws IOException, TimeoutException {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    final Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String json = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + json + "'");

        ObjectMapper mapper = new ObjectMapper();
        User deserializeUser = deserialize(mapper, json);
        persist(deserializeUser);

      }
    };
    // basicConsume(String queue, boolean autoAck, Consumer callback)
    channel.basicConsume(TASK_QUEUE_NAME, true, consumer);
  }

  private static User deserialize(ObjectMapper mapper, String jsonString) throws java.io.IOException {
    System.out.println("#deserialize");
    User student = mapper.readValue(jsonString, User.class);
    System.out.println(student);
    return student;
  }

  private static void persist(User user) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      UserDao userDao = new UserJpaDao();
      userDao.setEntityManager(entityManager);

      userDao.save(user);

      entityManager.getTransaction().commit();
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    } finally {
      entityManager.close();
      entityManagerFactory.close();
    }
  }
}
/*
[*] Waiting for messages. To exit press CTRL+C
 [x] Received '{
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
#deserialize
User(userId=null, firstName=Tom, lastName=Lee, birthDate=Wed Oct 11 22:29:43 PDT 2017, emailAddress=tom168@yahoo.com, lastUpdatedDate=Wed Oct 11 22:29:43 PDT 2017, lastUpdatedBy=tom, createdDate=Wed Oct 11 22:29:43 PDT 2017, createdBy=tom)
Wed Oct 11 22:29:44 PDT 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
 */
