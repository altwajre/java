package com.company.app;

import com.company.app.model.Address;
import com.company.app.model.Bank;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209934.html

describe bank;
+-------------------+--------------+------+-----+---------+----------------+
| Field             | Type         | Null | Key | Default | Extra          |
+-------------------+--------------+------+-----+---------+----------------+
| BANK_ID           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| NAME              | varchar(100) | NO   |     | NULL    |                |
| ADDRESS_LINE_1    | varchar(100) | NO   |     | NULL    |                |
| ADDRESS_LINE_2    | varchar(100) | NO   |     | NULL    |                |
| CITY              | varchar(100) | NO   |     | NULL    |                |
| STATE             | varchar(2)   | NO   |     | NULL    |                |
| ZIP_CODE          | varchar(5)   | NO   |     | NULL    |                |
| IS_INTERNATIONAL  | tinyint(1)   | NO   |     | NULL    |                |
| LAST_UPDATED_BY   | varchar(45)  | NO   |     | NULL    |                |
| LAST_UPDATED_DATE | datetime     | NO   |     | NULL    |                |
| CREATED_BY        | varchar(45)  | NO   |     | NULL    |                |
| CREATED_DATE      | datetime     | NO   |     | NULL    |                |
| ADDRESS_TYPE      | varchar(45)  | YES  |     | NULL    |                |
+-------------------+--------------+------+-----+---------+----------------+
13 rows in set (0.00 sec)

describe user_address;
+---------------------+--------------+------+-----+---------+-------+
| Field               | Type         | Null | Key | Default | Extra |
+---------------------+--------------+------+-----+---------+-------+
| USER_ID             | bigint(20)   | NO   | PRI | NULL    |       |
| USER_ADDRESS_LINE_1 | varchar(100) | NO   | PRI | NULL    |       |
| USER_ADDRESS_LINE_2 | varchar(100) | NO   | PRI | NULL    |       |
| CITY                | varchar(100) | NO   | PRI | NULL    |       |
| STATE               | varchar(2)   | NO   | PRI | NULL    |       |
| ZIP_CODE            | varchar(5)   | NO   | PRI | NULL    |       |
| ADDRESS_TYPE        | varchar(45)  | YES  |     | NULL    |       |
+---------------------+--------------+------+-----+---------+-------+

-----------------------------------------------------

> Embeddable class does not have @Id

@Embeddable
public class Address {
  @Column(name = "ADDRESS_LINE_1")
  private String addressLine1;

> Embedded Address

@Entity
@Table(name = "bank")
public class Bank {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="BANK_ID")
  private Long bankId;

  @Embedded
  private Address address = new Address();


 */
public class App {
  public static void main(String[] args) {
    session();

//    entityManager(); // create hibernate_sequence table

  }

  private static void entityManager() {
    String persistenceProviderName = "test-unit";
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceProviderName);
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("kevin");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385@yahoo.com");
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  private static void session() {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("kevin");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385@yahoo.com");
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    session.save(user);

//    Bank bank = new Bank();
//    bank.setName("Federal Trust");
//    bank.setInternational(false);
//    bank.setCreatedBy("Kevin");
//    bank.setCreatedDate(new Date());
//    bank.setLastUpdatedBy("Kevin");
//    bank.setLastUpdatedDate(new Date());

//    Address address = new Address();
//    address.setAddressLine1("line 1");
//    address.setAddressLine2("line2");
//    address.setCity("Philadelphia");
//    address.setState("PA");
//    address.setZipCode("12345");
//
//    bank.setAddress(address);

//    session.save(bank);

    session.getTransaction().commit();

    sessionFactory.close();
  }
}
