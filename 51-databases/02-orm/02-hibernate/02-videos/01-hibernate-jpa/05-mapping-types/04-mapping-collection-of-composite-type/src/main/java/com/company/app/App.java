package com.company.app;

import com.company.app.model.Address;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209937.html

> SQL tables

mysql> describe finances_user;
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
14 rows in set (0.00 sec)

mysql> describe user_address;
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

> Object model - class Bank maps to table `bank` and `bank_contact`

@Entity
@Table(name = "finances_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long userId;

  @ElementCollection
  @CollectionTable(name = "user_address", joinColumns = @JoinColumn(name = "USER_ID"))
  @AttributeOverrides({@AttributeOverride(name = "addressLine1", column = @Column(name = "USER_ADDRESS_LINE_1")),
      @AttributeOverride(name = "addressLine2", column = @Column(name = "USER_ADDRESS_LINE_2"))})
  private List<Address> addresses = new ArrayList<>();

-----------------------------------------------------

1. resources/hibernate.cfg.xml - it is loaded by .applySettings(configuration.getProperties())
<hibernate-configuration>
  <session-factory>
    <!-- Database connection settings -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/ifinances</property>
    <property name="connection.username">infinite</property>
    <property name="connection.password">skills</property>
    <!-- SQL dialect -->
    <property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
    <!-- Echo all executed SQL to stdout -->
    <property name="show_sql">true</property>
  </session-factory>
</hibernate-configuration>

2, add User to config and load xml config
    Configuration configuration = new Configuration();
    configuration.addAnnotatedClass(User.class);
    configuration.configure();
    return configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()) // load xml config
            .build());

3, run InfiniteFinancesSchema.sql first before running this app

4, database - finances_user table

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
    )

DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `USER_ID` bigint(20) NOT NULL,
  `USER_ADDRESS_LINE_1` varchar(100) NOT NULL,
  `USER_ADDRESS_LINE_2` varchar(100) NOT NULL,
  `CITY` varchar(100) NOT NULL,
  `STATE` varchar(2) NOT NULL,
  `ZIP_CODE` varchar(5) NOT NULL,
  `ADDRESS_TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`USER_ADDRESS_LINE_1`,`USER_ADDRESS_LINE_2`,`CITY`,`STATE`,`ZIP_CODE`)
)
 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("kmb");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385");
    user.setFirstName("Kevin");
    user.setLastName("bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    Address address1 = new Address();
    address1.setAddressLine1("Line 1");
    address1.setAddressLine2("Line 2");
    address1.setCity("New York");
    address1.setState("NY");
    address1.setZipCode("12345");

    user.getAddresses().add(address1);

    Address address2 = new Address();
    address2.setAddressLine1("Line 3");
    address2.setAddressLine2("Line 4");
    address2.setCity("Corning");
    address2.setState("NY");
    address2.setZipCode("12345");

    user.getAddresses().add(address2);

    session.save(user);
    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into user_address (USER_ID, USER_ADDRESS_LINE_1, USER_ADDRESS_LINE_2, CITY, STATE, ZIP_CODE) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into user_address (USER_ID, USER_ADDRESS_LINE_1, USER_ADDRESS_LINE_2, CITY, STATE, ZIP_CODE) values (?, ?, ?, ?, ?, ?)
 */
