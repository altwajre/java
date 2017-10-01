package com.company.app;

import com.company.app.model.Credential;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209941.html

bidirectional one to one

> SQL tables

- Source Object (credential)/Target Object (finances_user)

describe credential;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| CREDENTIAL_ID | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| USER_ID       | bigint(20)   | NO   | UNI | NULL    |                |
| USERNAME      | varchar(50)  | NO   |     | NULL    |                |
| PASSWORD      | varchar(100) | NO   |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+

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

> Object model - classes

@Entity
@Table(name="credential")
public class Credential {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CREDENTIAL_ID")
	public Long credentialId;

	@OneToOne(cascade=CascadeType.ALL)
	// name="USER_ID" - `credential` table fk `USER_ID`
	// referencedColumnName = "USER_ID" - `finances_user` table pk USER_ID
	@JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
	public User user;

@Entity
@Table(name = "finances_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long userId;

  @OneToOne(mappedBy = "user")
  private Credential credential;

App
    credential.setUser(user);
    user.setCredential(credential);

    session.save(credential);
    session.getTransaction().commit();

    User dbUser = session.get(User.class, credential.getUser().getUserId());
    System.out.println(dbUser.getFirstName());

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
    configuration.addAnnotatedClass(User.class);
    configuration.addAnnotatedClass(Credential.class);
    configuration.configure();
    return configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()) // load xml config
            .build());

3, run InfiniteFinancesSchema.sql first before running this app

4, database - finances_user table

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `finances_user`;
SET FOREIGN_KEY_CHECKS=1;
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

DROP TABLE IF EXISTS `credential`;
CREATE TABLE `credential` (
  `CREDENTIAL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  PRIMARY KEY (`CREDENTIAL_ID`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USER_ID`),
  CONSTRAINT `FINANCES_USER_FK` FOREIGN KEY (`USER_ID`) REFERENCES `finances_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    User user = new User();
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setBirthDate(new Date());
    user.setCreatedBy("Kevin Bowersox");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kevin.bowersox@navy.mil");
    user.setLastUpdatedDate(new Date());
    user.setLastUpdatedBy("Kevin Bowersox");

    Credential credential = new Credential();
    credential.setPassword("kevinspassword");
    credential.setUsername("kmb385");

    credential.setUser(user);
    user.setCredential(credential);

    session.save(credential);
    session.getTransaction().commit();

    User dbUser = session.get(User.class, credential.getUser().getUserId());
    System.out.println(dbUser.getFirstName());

    sessionFactory.close();
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into credential (PASSWORD, USER_ID, USERNAME, CREDENTIAL_ID) values (?, ?, ?, ?)
Kevin
 */
