package com.company.app;

import com.company.app.dao.CustomerJpaDao;
import com.company.app.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

// use this to debug mysql
public class MySqlApp {
  private static ObjectMapper MAPPER = new ObjectMapper();
  public static void main(String[] args) {

    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("test-unit");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      CustomerJpaDao customerDao = new CustomerJpaDao();
      customerDao.setEntityManager(entityManager);

      Customer customer = new Customer();
      customer.setName("Tom");
      customer.setAge(18);

      // CREATE
      Customer createdCustomer = customerDao.save(customer);
      System.out.println(createdCustomer.getId());

      // GET one
      Long customerId = createdCustomer.getId();
      Customer foundCustomer = customerDao.findById(customerId);
      System.out.println(foundCustomer);

      // UPDATE
      foundCustomer.setName("Dick");
      Customer updatedCustomer = customerDao.save(foundCustomer);
      System.out.println(updatedCustomer);

      entityManager.getTransaction().commit();

      // Native Query
      nativeQuery(entityManager);

    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    } finally {
      entityManager.close();
      entityManagerFactory.close();
    }
  }

  private static void nativeQuery(EntityManager entityManager) {
    entityManager.getTransaction().begin();
    Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM Customer");
    List resultList = nativeQuery.getResultList();
    resultList.forEach(u -> {
      try {
        System.out.println(MAPPER.writeValueAsString(u));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });
    entityManager.getTransaction().commit();
  }

}
