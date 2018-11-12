package com.company.app;

import com.company.app.dao.CustomerDao;
import com.company.app.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

// use this to debug mysql
public class CompareDatabaseSnapshotApp {
  private static ObjectMapper MAPPER = new ObjectMapper();
  public static void main(String[] args) {
    ObjectMapper mapper = new ObjectMapper();

    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("test-unit");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      CustomerDao customerDao = new CustomerDao();
      customerDao.setEntityManager(entityManager);

      Customer customer = new Customer();
//      customer.setId(1L);
      customer.setName("New");
      customer.setAge(18);

      // CREATE
      Customer createdCustomer = customerDao.save(customer);
      System.out.println(createdCustomer.getId());

      // GET one
      Long customerId = createdCustomer.getId();
      Customer currentObj = customerDao.findById(customerId);

      JsonNode baselineJson = ResourceHelper.get("data/customer.json");
      Customer baselineObj = mapper.treeToValue(baselineJson, Customer.class);
      List<Customer> baselineList = new ArrayList<>();
      baselineList.add(baselineObj);

      JsonNode currentJson = mapper.convertValue(currentObj, JsonNode.class);
      System.out.println(currentJson);

      Javers javers = JaversBuilder.javers().build();
      System.out.println("# Compare Objects");
      Diff diff = javers.compare(baselineObj, currentObj);
      System.out.println(diff);

      System.out.println("# Compare JsonNodes");
      Diff diffJson = javers.compare(baselineJson, currentJson);
      System.out.println(diffJson.toString());

      List<Customer> currentList = customerDao.findAll();
      System.out.println(currentList);
      System.out.println("# Compare Objects list");
      Diff diffObjs = javers.compareCollections(baselineList, currentList, Customer.class);
      System.out.println(diffObjs);

      // UPDATE
      currentObj.setName("Harry2");
      Customer updatedCustomer = customerDao.save(currentObj);
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
