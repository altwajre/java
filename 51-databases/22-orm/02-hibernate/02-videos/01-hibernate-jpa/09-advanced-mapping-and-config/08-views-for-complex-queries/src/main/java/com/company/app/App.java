package com.company.app;

import com.company.app.model.Credential;
import com.company.app.model.User;
import com.company.app.model.UserCredentialView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209974.html

use views for read-only complex queries

> SQL view

describe v_user_credential;
+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| user_id    | bigint(20)   | NO   |     | 0       |       |
| FIRST_NAME | varchar(45)  | NO   |     | NULL    |       |
| last_name  | varchar(45)  | NO   |     | NULL    |       |
| USERNAME   | varchar(50)  | NO   |     | NULL    |       |
| password   | varchar(100) | NO   |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `infinite`@`%`
    SQL SECURITY DEFINER
VIEW `v_user_credential` AS
    SELECT
        `finances_user`.`USER_ID` AS `user_id`,
        `finances_user`.`FIRST_NAME` AS `FIRST_NAME`,
        `finances_user`.`LAST_NAME` AS `last_name`,
        `credential`.`USERNAME` AS `USERNAME`,
        `credential`.`PASSWORD` AS `password`
    FROM
        (`finances_user`
        JOIN `credential` ON ((`finances_user`.`USER_ID` = `credential`.`USER_ID`)))

run InfiniteFinancesSchema.sql first before running this app

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      addRecordToViewTables(entityManager);

      UserCredentialView userCredentialView = entityManager.find(UserCredentialView.class, 1L);
      System.out.println(userCredentialView.getFirstName());
      System.out.println(userCredentialView.getLastName());

      entityManager.getTransaction().commit();

    }
    catch (Exception e){
      entityManager.getTransaction().rollback();
    }
    finally {
      entityManager.close();
      entityManagerFactory.close();
    }
  }

  private static void addRecordToViewTables(EntityManager entityManager) {
    User user = new User();
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setBirthDate(new Date());
    user.setCreatedBy("Kevin Bowersox");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kevin.bowersox@navy.mil");
    user.setLastUpdatedDate(new Date());
    user.setLastUpdatedBy("Kevin Bowersox");

    Credential credential = new Credential();
    credential.setPassword("kevinspassword");
    credential.setUsername("kmb385");

    credential.setUser(user);
    user.setCredential(credential);

    entityManager.persist(credential);
    entityManager.flush();
  }
}
/*
Hibernate: select usercreden0_.USER_ID as USER_ID1_0_0_, usercreden0_.FIRST_NAME as FIRST_NA2_0_0_, usercreden0_.LAST_NAME as LAST_NAM3_0_0_, usercreden0_.PASSWORD as PASSWORD4_0_0_, usercreden0_.USERNAME as USERNAME5_0_0_ from v_user_credential usercreden0_ where usercreden0_.USER_ID=?
 */
