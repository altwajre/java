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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/configuration.html

Map<String, Object> configOverrides = new HashMap<String, Object>();
configOverrides.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/ifinances");
entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances", configOverrides);

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209973.html

> SQL tables

describe finances_user;
+---------------------+--------------+------+-----+---------+----------------+
| Field               | Type         | Null | Key | Default | Extra          |
+---------------------+--------------+------+-----+---------+----------------+
| USER_ID             | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| FIRST_NAME          | varchar(45)  | NO   |     | NULL    |                |
| LAST_NAME           | varchar(45)  | NO   |     | NULL    |                |
| BIRTH_DATE          | date         | NO   |     | NULL    |                |
| EMAIL_ADDRESS       | varchar(100) | NO   |     | NULL    |                |
| LAST_UPDATED_BY     | varchar(45)  | NO   |     | NULL    |                |
| LAST_UPDATED_DATE   | datetime     | NO   |     | NULL    |                |
| CREATED_BY          | varchar(45)  | NO   |     | NULL    |                |
| CREATED_DATE        | datetime     | NO   |     | NULL    |                |
| USER_ADDRESS_LINE_1 | varchar(100) | YES  |     | NULL    |                |
| USER_ADDRESS_LINE_2 | varchar(100) | YES  |     | NULL    |                |
| CITY                | varchar(100) | YES  |     | NULL    |                |
| STATE               | varchar(2)   | YES  |     | NULL    |                |
| ZIP_CODE            | varchar(5)   | YES  |     | NULL    |                |
+---------------------+--------------+------+-----+---------+----------------+

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `finances_user`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `finances_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `EMAIL_ADDRESS` varchar(100) NOT NULL,
  `LAST_UPDATED_BY` varchar(45) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `USER_ADDRESS_LINE_1` varchar(100) DEFAULT NULL,
  `USER_ADDRESS_LINE_2` varchar(100) DEFAULT NULL,
  `CITY` varchar(100) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ZIP_CODE` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
);

it covers CRUD
- create
- read
- update
- delete

 */
public class App {
  private static ObjectMapper MAPPER = new ObjectMapper();
  public static void main(String[] args) {

    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      Map<String, Object> configOverrides = new HashMap<String, Object>();
      configOverrides.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/ifinances");

      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances", configOverrides);
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
/*
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
Create - UserId=17
Read - User FirstName=Tom
Update - UserId=17, FirstName=Tommy
Hibernate: update finances_user set BIRTH_DATE=?, EMAIL_ADDRESS=?, FIRST_NAME=?, LAST_NAME=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=? where USER_ID=?
Hibernate: SELECT * FROM ifinances.finances_user
[1,"Tommy","Lee","2017-10-17","tom168@yahoo.com","tom",1508370532000,"tom",1508370532000,null,null,null,null,null]
 */
