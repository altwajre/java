package com.company.app;

import com.company.app.dao.UserJpaDao;
import com.company.app.dao.interfaces.UserDao;
import com.company.app.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/*
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
| OPTLOCK             | bigint(20)   | NO   |     | NULL    |                |
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
  `OPTLOCK` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`)
)

it covers CRUD
- create
- read
- update

 */
public class App {
  public static void main(String[] args) {

    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();

      System.out.println("# Transaction_1 - user1 read the same record and update first success!");
      entityManager.getTransaction().begin();

      UserDao userDao = new UserJpaDao();
      userDao.setEntityManager(entityManager);

      User createUser = create(userDao);
      System.out.println("Create - UserId=" + createUser.getUserId());

      Long userId = createUser.getUserId();
      User readUser1 = read(userDao, userId);
      System.out.println("readUser1 FirstName=" + readUser1.getFirstName() + ", version="+readUser1.getOptLock());
      String userJson = GlobalMapper.INSTANCE.mapper().writeValueAsString(readUser1);
      System.out.println(userJson);

      User deserializedUser1 = GlobalMapper.INSTANCE.mapper().readValue(userJson, User.class);
      System.out.println("deserializedUser1 FirstName=" + deserializedUser1.getFirstName() + ", version="+deserializedUser1.getOptLock());

      deserializedUser1.setFirstName("Dick");
      entityManager.merge(deserializedUser1); // save detached entity

      entityManager.getTransaction().commit();

      // Transaction_2
      System.out.println("# Transaction_2 - user2 read the same record and update second fail due to conflict");
      entityManager.getTransaction().begin();

      User readUser2 = read(userDao, userId);
      System.out.println("readUser2 FirstName=" + readUser2.getFirstName() + ", version="+readUser2.getOptLock());

      User deserializedUser2 = GlobalMapper.INSTANCE.mapper().readValue(userJson, User.class);
      System.out.println("deserializedUser2 FirstName=" + deserializedUser2.getFirstName() + ", version="+deserializedUser2.getOptLock());

      deserializedUser1.setFirstName("Harry");
      entityManager.merge(deserializedUser2); // save detached entity

      entityManager.getTransaction().commit();

    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      entityManager.close();
      entityManagerFactory.close();
    }
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
# Transaction_1 - user1 read the same record and update first
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE, OPTLOCK) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
Create - UserId=22
readUser1 FirstName=Tom, version=1
{"userId":22,"firstName":"Tom","lastName":"Lee","birthDate":"2017-10-13T01:01:04.447+0000","emailAddress":"tom168@yahoo.com","lastUpdatedDate":"2017-10-13T01:01:04.447+0000","lastUpdatedBy":"tom","createdDate":"2017-10-13T01:01:04.447+0000","createdBy":"tom","optLock":1}
deserializedUser1 FirstName=Tom, version=1
Hibernate: update finances_user set BIRTH_DATE=?, EMAIL_ADDRESS=?, FIRST_NAME=?, LAST_NAME=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=?, OPTLOCK=? where USER_ID=? and OPTLOCK=?
# Transaction_2 - user2 read the same record and update second will conflict
readUser2 FirstName=Dick, version=2
deserializedUser2 FirstName=Tom, version=1
javax.persistence.OptimisticLockException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [com.company.app.model.User#22]
	at org.hibernate.internal.ExceptionConverterImpl.wrapStaleStateException(ExceptionConverterImpl.java:200)
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:86)
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:155)
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:162)
	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:893)
	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:867)
	at com.company.app.App.main(App.java:110)
Caused by: org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [com.company.app.model.User#22]
	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsDetached(DefaultMergeEventListener.java:322)
	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:170)
	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:69)
	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:881)
	... 2 more
 */
