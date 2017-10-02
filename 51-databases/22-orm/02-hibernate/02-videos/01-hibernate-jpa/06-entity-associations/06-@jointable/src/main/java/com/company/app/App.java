package com.company.app;

/*

when few of the transactions belong to budget, we create `budget_transaction` table to avoid lots of null budget value on `transaction` table

-----

show create table transaction;

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

-----

show create table budget;

CREATE TABLE `budget` (
  `BUDGET_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `GOAL_AMOUNT` decimal(10,2) NOT NULL,
  `PERIOD` varchar(45) NOT NULL,
  PRIMARY KEY (`BUDGET_ID`)
)

describe budget;
+-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
| BUDGET_ID   | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| NAME        | varchar(100)  | NO   |     | NULL    |                |
| GOAL_AMOUNT | decimal(10,2) | NO   |     | NULL    |                |
| PERIOD      | varchar(45)   | NO   |     | NULL    |                |
+-------------+---------------+------+-----+---------+----------------+

-----

show create table budget_transaction;

CREATE TABLE `budget_transaction` (
  `TRANSACTION_ID` bigint(20) NOT NULL,
  `BUDGET_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`,`BUDGET_ID`),
  UNIQUE KEY `TRANSACTION_ID_UNIQUE` (`TRANSACTION_ID`),
  KEY `BUDGET_FK_idx` (`BUDGET_ID`),
  CONSTRAINT `BUDGET_FK` FOREIGN KEY (`BUDGET_ID`) REFERENCES `budget` (`BUDGET_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TRANSACTION_FK2` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transaction` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

describe budget_transaction;
+----------------+------------+------+-----+---------+-------+
| Field          | Type       | Null | Key | Default | Extra |
+----------------+------------+------+-----+---------+-------+
| TRANSACTION_ID | bigint(20) | NO   | PRI | NULL    |       |
| BUDGET_ID      | bigint(20) | NO   | PRI | NULL    |       |
+----------------+------------+------+-----+---------+-------+

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
    configuration.addAnnotatedClass(Budget.class);
    configuration.addAnnotatedClass(Account.class);
    configuration.addAnnotatedClass(Transaction.class);
    configuration.configure();
    return configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()) // load xml config
            .build());

3, run InfiniteFinancesSchema.sql first before running this app

4, database

run InfiniteFinancesSchema.sql again to have a clean state

 */

import com.company.app.model.Account;
import com.company.app.model.Budget;
import com.company.app.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

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
    beltPurchase.setAccount(account);

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
    shoePurchase.setAccount(account);

    Budget budget = new Budget();
    budget.setGoalAmount(new BigDecimal("10000.00"));
    budget.setName("Emergency Fund");
    budget.setPeriod("Yearly");

    budget.getTransactions().add(beltPurchase);
    budget.getTransactions().add(shoePurchase);

    session.save(budget);
    session.getTransaction().commit();

    Transaction dbTransaction = session.get(Transaction.class, account.getTransactions().get(0).getTransactionId());
    System.out.println(dbTransaction.getAccount().getName());

    sessionFactory.close();
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into budget (GOAL_AMOUNT, NAME, PERIOD, BUDGET_ID) values (?, ?, ?, ?)
Hibernate: insert into account (CLOSE_DATE, CREATED_BY, CREATED_DATE, CURRENT_BALANCE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, OPEN_DATE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into transaction (ACCOUNT_ID, AMOUNT, CLOSING_BALANCE, CREATED_BY, CREATED_DATE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NOTES, TITLE, TRANSACTION_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into transaction (ACCOUNT_ID, AMOUNT, CLOSING_BALANCE, CREATED_BY, CREATED_DATE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NOTES, TITLE, TRANSACTION_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into budget_transaction (BUDGET_ID, TRANSACTION_ID) values (?, ?)
Hibernate: insert into budget_transaction (BUDGET_ID, TRANSACTION_ID) values (?, ?)
Savings Account
 */
