package com.company.app;

import com.company.app.model.Credential;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*

> SQL tables - one to one relationship

- Source Object (credential)/Target Object (finances_user)

describe credential;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| CREDENTIAL_ID | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| USER_ID       | bigint(20)   | NO   | UNI | NULL    |                |
| USERNAME      | varchar(50)  | NO   |     | NULL    |                |
| PASSWORD      | varchar(100) | NO   |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+

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


 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

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

    session.save(credential);
    session.getTransaction().commit();

    sessionFactory.close();
  }
}
