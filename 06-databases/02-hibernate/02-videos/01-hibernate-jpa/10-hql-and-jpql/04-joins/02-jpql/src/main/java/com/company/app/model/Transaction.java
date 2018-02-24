package com.company.app.model;

/*
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
 */


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRANSACTION_ID")
  private Long transactionId;

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="ACCOUNT_ID")
  private Account account;

  @Column(name = "TRANSACTION_TYPE")
  private String transactionType;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "INITIAL_BALANCE")
  private BigDecimal initialBalance;

  @Column(name = "CLOSING_BALANCE")
  private BigDecimal closingBalance;

  @Column(name = "NOTES")
  private String notes;

  @Column(name = "LAST_UPDATED_DATE")
  private Date lastUpdatedDate;

  @Column(name = "LAST_UPDATED_BY")
  private String lastUpdatedBy;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "CREATED_BY")
  private String createdBy;

}
