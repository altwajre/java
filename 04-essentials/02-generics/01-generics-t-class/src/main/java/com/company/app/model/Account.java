package com.company.app.model;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
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

Owning table contains the fk ACCOUNT_ID
 */

@Data
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
