package com.company.app;

import com.company.app.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/*
http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/

WARNs:
1, Hibernate: drop table if exists finances_user exception occurs
2, Hibernate: create table finances_user exception occurs

-----------------------------------------------------------------

> 4. Tutorial Using the Java Persistence API (JPA)
JPA, however, defines a differen
t bootstrap process that uses its own configuration file named persistence.ml. This bootstraping process is defined by
the JPA spec. In Java SE the persistence provider is required to locate all JPA configuration files by classpath lookup
of the META-INF/persistence.xml resource name.

persistence.xml files should provide a unique name for each "persistence unit". Applications use this name to reference
the configuration when obtaining an javax.persistence.EntityManagerFacory reference.

-----------------------------------------------------------------

1. resources/META-INF/persistence.xml - it is picked by Persistence.createEntityManagerFactory("test-unit");
  <persistence-unit name="test-unit">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
      <property name="javax.persistence.jdbc.driver"
                value="com.mysql.cj.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.url"
                value="jdbc:mysql://localhost/ifinances"/>
      <property name="javax.persistence.jdbc.user"
                value="infinite"/>
      <property name="javax.persistence.jdbc.password"
                value="skills"/>
      <property name="hibernate.show_sql"
                value="true"/>
      <property name="hibernate.hbm2ddl.auto"
                value="create"/>
    </properties>
  </persistence-unit>

2, add User to config
Configuration configuration = new Configuration();
configuration.addAnnotatedClass(User.class);
return configuration
    .buildSessionFactory(new StandardServiceRegistryBuilder()
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
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 */
public class App {
  public static void main(String[] args) {
    String persistenceProviderName = "test-unit";
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceProviderName);
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("kevin");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385@yahoo.com");
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();

    entityManagerFactory.close();
  }
}
/*
Hibernate: drop table if exists finances_user
Hibernate: create table finances_user (USER_ID bigint not null auto_increment, BIRTH_DATE datetime, CREATED_BY varchar(255), CREATED_DATE datetime, EMAIL_ADDRESS varchar(255), FIRST_NAME varchar(255), LAST_NAME varchar(255), LAST_UPDATED_BY varchar(255), LAST_UPDATED_DATE datetime, primary key (USER_ID)) engine=MyISAM
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
 */
