package com.company.app;

import com.company.app.model.Account;
import com.company.app.model.AccountType;
import com.company.app.model.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209968.html

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

> Object model

@Data
@Entity
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @Enumerated(EnumType.STRING)
  @Column(name="ACCOUNT_TYPE")
  private AccountType accountType;

-----------------------------------------------------

1, run InfiniteFinancesSchema.sql first before running this app

 */

public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Account account = new Account();
      account.setCloseDate(new Date());
      account.setOpenDate(new Date());
      account.setCreatedBy("Tom Lee");
      account.setInitialBalance(new BigDecimal("80.00"));
      account.setName("Savings Account");
      account.setCurrentBalance(new BigDecimal("128.00"));
      account.setLastUpdatedBy("Tom Lee");
      account.setLastUpdatedDate(new Date());
      account.setCreatedDate(new Date());

      account.setAccountType(AccountType.SAVINGS);
      entityManager.persist(account);

      entityManager.getTransaction().commit();
    }
    catch (Exception e){
      entityManager.getTransaction().rollback();
      e.printStackTrace();
    }
    finally {
      entityManager.close();
      entityManagerFactory.close();
    }
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into account (ACCOUNT_TYPE, CLOSE_DATE, CREATED_BY, CREATED_DATE, CURRENT_BALANCE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, OPEN_DATE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
 */
