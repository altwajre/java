package com.company.app;

import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209928.html

Use @Transient to ignore the field that is NOT a table column
  @Transient
  private boolean valid;

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
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("kevin");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385@yahoo.com");
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    session.save(user);
    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
 */
