package com.company.app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
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
 */

@Data
@Entity
@Table(name = "finances_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long userId;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "BIRTH_DATE", nullable = false)
  private Date birthDate;

  @Column(name = "EMAIL_ADDRESS")
  private String emailAddress;

  @ElementCollection
  @CollectionTable(name = "user_address", joinColumns = @JoinColumn(name = "USER_ID"))
  @AttributeOverrides({@AttributeOverride(name = "addressLine1", column = @Column(name = "USER_ADDRESS_LINE_1")),
      @AttributeOverride(name = "addressLine2", column = @Column(name = "USER_ADDRESS_LINE_2"))})
  private List<Address> addresses = new ArrayList<>();

  @Column(name = "LAST_UPDATED_DATE")
  private Date lastUpdatedDate;

  @Column(name = "LAST_UPDATED_BY")
  private String lastUpdatedBy;

  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

  @Column(name = "CREATED_BY", updatable = false)
  private String createdBy;
}
