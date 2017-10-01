package com.company.app;

import com.company.app.model.Account;
import com.company.app.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209942.html

unidirectional one to many

> SQL tables

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

describe transaction;
+-------------------+---------------+------+-----+---------+----------------+
| Field             | Type          | Null | Key | Default | Extra          |
+-------------------+---------------+------+-----+---------+----------------+
| TRANSACTION_ID    | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| ACCOUNT_ID        | bigint(20)    | NO   | MUL | NULL    |                |
| TRANSACTION_TYPE  | varchar(45)   | NO   |     | NULL    |                |
| TITLE             | varchar(100)  | NO   |     | NULL    |                |
| AMOUNT            | decimal(10,2) | NO   |     | NULL    |                |
| INITIAL_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| CLOSING_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| NOTES             | mediumtext    | YES  |     | NULL    |                |
| LAST_UPDATED_BY   | varchar(45)   | NO   |     | NULL    |                |
| LAST_UPDATED_DATE | datetime      | NO   |     | NULL    |                |
| CREATED_BY        | varchar(45)   | NO   |     | NULL    |                |
| CREATED_DATE      | datetime      | NO   |     | NULL    |                |
+-------------------+---------------+------+-----+---------+----------------+

> Object model - classes

@Entity
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="ACCOUNT_ID", nullable=false) // ACCOUNT_ID is transaction.ACCOUNT_ID
  List<Transaction> transactions = new ArrayList<>();

@Entity
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRANSACTION_ID")
  private Long transactionId;

  @Column(name = "TRANSACTION_TYPE")
  private String transactionType;

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
    configuration.addAnnotatedClass(Transaction.class);
    configuration.configure();
    return configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()) // load xml config
            .build());

3, run InfiniteFinancesSchema.sql first before running this app

4, database - finances_user table

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `account`;
SET FOREIGN_KEY_CHECKS=1;
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

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `transaction`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `transaction` (
  `TRANSACTION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` bigint(20) NOT NULL,
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `TITLE` varchar(100) NOT NULL,
  `AMOUNT` decimal(10,2) NOT NULL,
  `INITIAL_BALANCE` decimal(10,2) NOT NULL,
  `CLOSING_BALANCE` decimal(10,2) NOT NULL,
  `NOTES` mediumtext,
  `LAST_UPDATED_BY` varchar(45) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  KEY `ACCOUNT_FK2_idx` (`ACCOUNT_ID`),
  CONSTRAINT `ACCOUNT_FK2` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Account account = new Account();
    account.setCloseDate(new Date());
    account.setOpenDate(new Date());
    account.setCreatedBy("Kevin Bowersox");
    account.setInitialBalance(new BigDecimal("50.00"));
    account.setName("Savings Account");
    account.setCurrentBalance(new BigDecimal("100.00"));
    account.setLastUpdatedBy("Kevin Bowersox");
    account.setLastUpdatedDate(new Date());
    account.setCreatedDate(new Date());

    Transaction beltPurchase = new Transaction();
    beltPurchase.setTitle("Dress Belt");
    beltPurchase.setAmount(new BigDecimal("50.00"));
    beltPurchase.setClosingBalance(new BigDecimal("0.00"));
    beltPurchase.setCreatedBy("Kevin Bowersox");
    beltPurchase.setCreatedDate(new Date());
    beltPurchase.setInitialBalance(new BigDecimal("0.00"));
    beltPurchase.setLastUpdatedBy("Kevin Bowersox");
    beltPurchase.setLastUpdatedDate(new Date());
    beltPurchase.setNotes("New Dress Belt");
    beltPurchase.setTransactionType("Debit");

    account.getTransactions().add(beltPurchase);

    Transaction shoePurchase = new Transaction();
    shoePurchase.setTitle("Work Shoes");
    shoePurchase.setAmount(new BigDecimal("100.00"));
    shoePurchase.setClosingBalance(new BigDecimal("0.00"));
    shoePurchase.setCreatedBy("Kevin Bowersox");
    shoePurchase.setCreatedDate(new Date());
    shoePurchase.setInitialBalance(new BigDecimal("0.00"));
    shoePurchase.setLastUpdatedBy("Kevin Bowersox");
    shoePurchase.setLastUpdatedDate(new Date());
    shoePurchase.setNotes("Nice Pair of Shoes");
    shoePurchase.setTransactionType("Debit");

    account.getTransactions().add(shoePurchase);

    session.save(account);
    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into account (CLOSE_DATE, CREATED_BY, CREATED_DATE, CURRENT_BALANCE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, OPEN_DATE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into transaction (AMOUNT, CLOSING_BALANCE, CREATED_BY, CREATED_DATE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NOTES, TITLE, TRANSACTION_TYPE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into transaction (AMOUNT, CLOSING_BALANCE, CREATED_BY, CREATED_DATE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NOTES, TITLE, TRANSACTION_TYPE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: update transaction set ACCOUNT_ID=? where TRANSACTION_ID=?
Hibernate: update transaction set ACCOUNT_ID=? where TRANSACTION_ID=?
 */
