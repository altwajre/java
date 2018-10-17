package com.company.app;

import com.company.app.dao.UserJpaDao;
import com.company.app.dao.interfaces.UserDao;
import com.company.app.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class MySqlApp {
  private static ObjectMapper MAPPER = new ObjectMapper();
  public static void main(String[] args) {

    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      UserDao userDao = new UserJpaDao();
      userDao.setEntityManager(entityManager);

      User createdUser = create(userDao);
      System.out.println("Create - UserId=" + createdUser.getUserId());

      Long userId = createdUser.getUserId();
      User foundUser = read(userDao, userId);
      System.out.println("Read - User FirstName=" + foundUser.getFirstName());

      foundUser.setFirstName("Tommy");
      User updatedUser = update(userDao, foundUser);
      System.out.println("Update - UserId=" + updatedUser.getUserId() + ", FirstName=" + updatedUser.getFirstName());

//      delete(userDao, updatedUser);

      entityManager.getTransaction().commit();

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
    Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM ifinances.finances_user");
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

  private static void delete(UserDao userDao, User updatedUser) {
    userDao.delete(updatedUser);
  }

  private static User update(UserDao userDao, User foundUser) {
    return userDao.save(foundUser);
  }

  private static User read(UserDao userDao, Long userId) {
    return userDao.findById(userId);
  }

  private static User create(UserDao userDao) {
    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("tom");
    user.setCreatedDate(new Date());
    user.setEmailAddress("tom168@yahoo.com");
    user.setFirstName("Tom");
    user.setLastName("Lee");
    user.setLastUpdatedBy("tom");
    user.setLastUpdatedDate(new Date());

    return userDao.save(user);
  }
}
