package com.company.app;

import com.company.app.model.Bank;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209935.html

> SQL tables

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

describe bank_contact;
+---------------+--------------+------+-----+---------+-------+
| Field         | Type         | Null | Key | Default | Extra |
+---------------+--------------+------+-----+---------+-------+
| BANK_ID       | bigint(20)   | NO   | PRI | NULL    |       |
| NAME          | varchar(100) | NO   | PRI | NULL    |       |
| POSITION_TYPE | varchar(100) | YES  |     | NULL    |       |
+---------------+--------------+------+-----+---------+-------+

> Object model - class Bank maps to table `bank` and `bank_contact`

@Data
@Entity
@Table(name = "bank")
public class Bank {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BANK_ID")
  private Long bankId;

  @Embedded
  private Address address = new Address();

  @ElementCollection
  @CollectionTable(name = "bank_contact", joinColumns = @JoinColumn(name = "BANK_ID"))
  @Column(name = "NAME")
  private Collection<String> contacts = new ArrayList<>();

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
    configuration.addAnnotatedClass(Bank.class);
    configuration.configure();
    return configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()) // load xml config
            .build());

3, run InfiniteFinancesSchema.sql first before running this app

4, database - `bank` and `bank_contact` tables

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `bank`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `bank` (
  `BANK_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ADDRESS_LINE_1` varchar(100) NOT NULL,
  `ADDRESS_LINE_2` varchar(100) NOT NULL,
  `CITY` varchar(100) NOT NULL,
  `STATE` varchar(2) NOT NULL,
  `ZIP_CODE` varchar(5) NOT NULL,
  `IS_INTERNATIONAL` tinyint(1) NOT NULL,
  `LAST_UPDATED_BY` varchar(45) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `ADDRESS_TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`BANK_ID`)
)

DROP TABLE IF EXISTS `bank_contact`;
CREATE TABLE `bank_contact` (
  `BANK_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `POSITION_TYPE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BANK_ID`,`NAME`)
)

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Bank bank = new Bank();
    bank.setName("Federal Trust");
    bank.setAddressLine1("33 Wall Street");
    bank.setAddressLine2("Suite 233");
    bank.setCity("New York");
    bank.setState("NY");
    bank.setZipCode("12345");
    bank.setInternational(false);
    bank.setCreatedBy("Kevin");
    bank.setCreatedDate(new Date());
    bank.setLastUpdatedBy("Kevin");
    bank.setLastUpdatedDate(new Date());
    bank.getContacts().add("Joe");
    bank.getContacts().add("Mary");
    session.save(bank);

    session.save(bank);

    session.getTransaction().commit();

    sessionFactory.close();
  }
}
