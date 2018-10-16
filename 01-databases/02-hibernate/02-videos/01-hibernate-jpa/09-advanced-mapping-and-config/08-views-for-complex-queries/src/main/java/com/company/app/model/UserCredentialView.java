package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
describe v_user_credential;
+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| user_id    | bigint(20)   | NO   |     | 0       |       |
| FIRST_NAME | varchar(45)  | NO   |     | NULL    |       |
| last_name  | varchar(45)  | NO   |     | NULL    |       |
| USERNAME   | varchar(50)  | NO   |     | NULL    |       |
| password   | varchar(100) | NO   |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `infinite`@`%`
    SQL SECURITY DEFINER
VIEW `v_user_credential` AS
    SELECT
        `finances_user`.`USER_ID` AS `user_id`,
        `finances_user`.`FIRST_NAME` AS `FIRST_NAME`,
        `finances_user`.`LAST_NAME` AS `last_name`,
        `credential`.`USERNAME` AS `USERNAME`,
        `credential`.`PASSWORD` AS `password`
    FROM
        (`finances_user`
        JOIN `credential` ON ((`finances_user`.`USER_ID` = `credential`.`USER_ID`)))

 */
@Data
@Entity
@Table(name = "v_user_credential")
public class UserCredentialView {

  @Id
  @Column(name="USER_ID")
  private Long userId;

  @Column(name="FIRST_NAME")
  private String firstName;

  @Column(name="LAST_NAME")
  private String lastName;

  @Column(name="USERNAME")
  private String username;

  @Column(name="PASSWORD")
  private String password;
}
