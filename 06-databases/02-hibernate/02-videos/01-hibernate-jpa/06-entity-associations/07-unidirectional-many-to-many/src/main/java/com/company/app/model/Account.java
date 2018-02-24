package com.company.app.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/*

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

 */

@Data
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

  @OneToMany(cascade=CascadeType.ALL, mappedBy = "account")
  List<Transaction> transactions = new ArrayList<>();

  @Column(name = "NAME")
  private String name;

  @Column(name = "INITIAL_BALANCE")
  private BigDecimal initialBalance;

  @Column(name = "CURRENT_BALANCE")
  private BigDecimal currentBalance;

  @Column(name = "OPEN_DATE")
  private Date openDate;

  @Column(name = "CLOSE_DATE")
  private Date closeDate;

  @Column(name = "LAST_UPDATED_DATE")
  private Date lastUpdatedDate;

  @Column(name = "LAST_UPDATED_BY")
  private String lastUpdatedBy;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "CREATED_BY")
  private String createdBy;
}
