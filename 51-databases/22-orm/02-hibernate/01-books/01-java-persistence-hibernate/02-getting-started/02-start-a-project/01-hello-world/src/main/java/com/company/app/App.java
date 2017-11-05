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
    entityManager.getTransaction().begin();

    Message message = new Message(); // Message table
    message.setText("Hello World!");

    entityManager.persist(message);

    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into Message (text, id) values (?, ?)
 */
