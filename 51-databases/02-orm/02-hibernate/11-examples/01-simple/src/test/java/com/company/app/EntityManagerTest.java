package com.company.app;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {
  @Test
  public void testPersist() {
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    Person person = new Person();
    person.setName("Tom");
    entityManager.getTransaction().begin();
    entityManager.persist(person);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
}
