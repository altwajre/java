package com.company.app.model;

/*
describe account_type;
+-------------------+-------------+------+-----+---------+----------------+
| Field             | Type        | Null | Key | Default | Extra          |
+-------------------+-------------+------+-----+---------+----------------+
| ACCOUNT_TYPE_ID   | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| NAME              | varchar(45) | YES  |     | NULL    |                |
| LAST_UPDATED_DATE | date        | YES  |     | NULL    |                |
| LAST_UPDATED_BY   | varchar(45) | YES  |     | NULL    |                |
| CREATED_DATE      | date        | YES  |     | NULL    |                |
| CREATED_BY        | varchar(45) | YES  |     | NULL    |                |
+-------------------+-------------+------+-----+---------+----------------+

CREATE TABLE `account_type` (
  `ACCOUNT_TYPE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `LAST_UPDATED_DATE` date DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_TYPE_ID`)
)
 */
public enum AccountType {
  LOAN,
  SAVINGS,
  CHECKING
}
