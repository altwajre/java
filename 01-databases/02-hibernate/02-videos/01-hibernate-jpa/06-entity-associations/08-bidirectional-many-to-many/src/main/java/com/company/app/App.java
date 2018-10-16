package com.company.app;

import com.company.app.model.Account;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209946.html

bidirectional many to many

> SQL tables

account is owning side for this many to many relationship

CREATE TABLE `account` (
  `ACCOUNT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BANK_ID` bigint(20) DEFAULT NULL,
  `ACCOUNT_TYPE` varchar(45) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `INITIAL_BALANCE` decimal(10,2) NOT NULL,
  `CURRENT_BALANCE` decimal(10,2) NOT NULL,
  `OPEN_DATE` date NOT NULL,
  `CLOSE_DATE` date NOT NULL,
  `LAST_UPDATED_BY` varchar(45) NOT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `BANK_FK` (`BANK_ID`),
  KEY `ACCOUNT_TYPE_FK_idx` (`ACCOUNT_TYPE`),
  CONSTRAINT `BANK_FK` FOREIGN KEY (`BANK_ID`) REFERENCES `bank` (`BANK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

describe account;
+-------------------+---------------+------+-----+---------+----------------+
| Field             | Type          | Null | Key | Default | Extra          |
+-------------------+---------------+------+-----+---------+----------------+
| ACCOUNT_ID        | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| BANK_ID           | bigint(20)    | YES  | MUL | NULL    |                |
| ACCOUNT_TYPE      | varchar(45)   | YES  | MUL | NULL    |                |
| NAME              | varchar(100)  | YES  |     | NULL    |                |
| INITIAL_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| CURRENT_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| OPEN_DATE         | date          | NO   |     | NULL    |                |
| CLOSE_DATE        | date          | NO   |     | NULL    |                |
| LAST_UPDATED_BY   | varchar(45)   | NO   |     | NULL    |                |
| LAST_UPDATED_DATE | datetime      | YES  |     | NULL    |                |
| CREATED_BY        | varchar(45)   | YES  |     | NULL    |                |
| CREATED_DATE      | datetime      | YES  |     | NULL    |                |
+-------------------+---------------+------+-----+---------+----------------+

-----

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

-----

CREATE TABLE `user_account` (
  `USER_ID` bigint(20) NOT NULL,
  `ACCOUNT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ACCOUNT_ID`),
  KEY `ACCOUNT_FK_idx` (`ACCOUNT_ID`),
  CONSTRAINT `ACCOUNT_FK` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USER_FK` FOREIGN KEY (`USER_ID`) REFERENCES `finances_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

describe user_account;
+------------+------------+------+-----+---------+-------+
| Field      | Type       | Null | Key | Default | Extra |
+------------+------------+------+-----+---------+-------+
| USER_ID    | bigint(20) | NO   | PRI | NULL    |       |
| ACCOUNT_ID | bigint(20) | NO   | PRI | NULL    |       |
+------------+------------+------+-----+---------+-------+

> Object model - classes

@Entity
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name="user_account",
      joinColumns=@JoinColumn(name="ACCOUNT_ID"), // Owning table (account) - ACCOUNT_ID = account.ACCOUNT_ID
      inverseJoinColumns=@JoinColumn(name="USER_ID")) // USER_ID = finances_user.USER_ID
  private Set<User> users = new HashSet<>();

@Entity
@Table(name = "finances_user")
public class User {
  @Id
  @GeneratedValue
  @Column(name = "USER_ID")
  private Long userId;

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users") // users = Account.users (Object)
  private Set<Account> accounts = new HashSet<>();

class App {
    ...
    user1.getAccounts().add(account1);
    user2.getAccounts().add(account1);

    user1.getAccounts().add(account2);
    user2.getAccounts().add(account2);

    session.save(user1);
    session.save(user2);
    ...
}
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
    configuration.addAnnotatedClass(Account.class);
    configuration.addAnnotatedClass(User.class);
    configuration.addAnnotatedClass(Transaction.class);
    configuration.addAnnotatedClass(Credential.class);
    configuration.configure();
    return configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()) // load xml config
            .build());

3, run InfiniteFinancesSchema.sql first before running this app


 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Account account1 = new Account();
    account1.setCloseDate(new Date());
    account1.setOpenDate(new Date());
    account1.setCreatedBy("Tom Lee");
    account1.setInitialBalance(new BigDecimal("20.00"));
    account1.setName("Savings Account");
    account1.setCurrentBalance(new BigDecimal("130.00"));
    account1.setLastUpdatedBy("Tom Lee");
    account1.setLastUpdatedDate(new Date());
    account1.setCreatedDate(new Date());

    Account account2 = new Account();
    account2.setCloseDate(new Date());
    account2.setOpenDate(new Date());
    account2.setCreatedBy("Dick Chen");
    account2.setInitialBalance(new BigDecimal("80.00"));
    account2.setName("Checking Account");
    account2.setCurrentBalance(new BigDecimal("160.00"));
    account2.setLastUpdatedBy("Dick Chen");
    account2.setLastUpdatedDate(new Date());
    account2.setCreatedDate(new Date());

    User user1 = new User();
    user1.setBirthDate(new Date());
    user1.setCreatedBy("Tom Lee");
    user1.setCreatedDate(new Date());
    user1.setEmailAddress("tom@test.com");
    user1.setFirstName("Tom");
    user1.setLastName("Lee");
    user1.setLastUpdatedBy("system");
    user1.setLastUpdatedDate(new Date());

    User user2 = new User();
    user2.setBirthDate(new Date());
    user2.setCreatedBy("Dick Chen");
    user2.setCreatedDate(new Date());
    user2.setEmailAddress("dick@test.com");
    user2.setFirstName("Dick");
    user2.setLastName("Chen");
    user2.setLastUpdatedBy("system");
    user2.setLastUpdatedDate(new Date());

//    account1.getUsers().add(user1);
//    account1.getUsers().add(user2);
    user1.getAccounts().add(account1);
    user2.getAccounts().add(account1);

//    account2.getUsers().add(user1);
//    account2.getUsers().add(user2);
    user1.getAccounts().add(account2);
    user2.getAccounts().add(account2);

    session.save(user1);
    session.save(user2);
    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE, USER_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into account (CLOSE_DATE, CREATED_BY, CREATED_DATE, CURRENT_BALANCE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, OPEN_DATE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into account (CLOSE_DATE, CREATED_BY, CREATED_DATE, CURRENT_BALANCE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, OPEN_DATE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE, USER_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
 */
