package com.company.app;

import com.company.app.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
  public static void main(String[] args) throws ClassNotFoundException {
//    Class.forName("com.mysql.cj.jdbc.Driver");
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    Message message = new Message();
    message.setText("Hello World!");

    entityManager.persist(message);
    entityManager.close();

  }
}
