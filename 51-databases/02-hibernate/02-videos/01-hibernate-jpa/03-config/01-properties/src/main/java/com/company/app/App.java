package com.company.app;

import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209920.html

1, resources/hibernate.properties - hibernate will auto pick up the hibernate.properties config
hibernate.connection.username=infinite
hibernate.connection.password=skills
hibernate.connection.url=jdbc:mysql://localhost:3306/ifinances
hibernate.connection.driver_class=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

2, add User to config
Configuration configuration = new Configuration();
configuration.addAnnotatedClass(User.class);
return configuration
    .buildSessionFactory(new StandardServiceRegistryBuilder()
        .build());

3, run InfiniteFinancesSchema.sql first before running this app

4, database - `finances_user` table

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
    session.beginTransaction();
//    session.getTransaction().begin();

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
DEBUG - Static select for entity com.company.app.model.User [OPTIMISTIC]: select user0_.USER_ID as USER_ID1_0_0_, user0_.BIRTH_DATE as BIRTH_DA2_0_0_, user0_.CREATED_BY as CREATED_3_0_0_, user0_.CREATED_DATE as CREATED_4_0_0_, user0_.EMAIL_ADDRESS as EMAIL_AD5_0_0_, user0_.FIRST_NAME as FIRST_NA6_0_0_, user0_.LAST_NAME as LAST_NAM7_0_0_, user0_.LAST_UPDATED_BY as LAST_UPD8_0_0_, user0_.LAST_UPDATED_DATE as LAST_UPD9_0_0_ from FINANCES_USER user0_ where user0_.USER_ID=?
DEBUG - Adding QuerySpace : uid = <gen:0> -> org.hibernate.loader.plan.build.internal.spaces.EntityQuerySpaceImpl@1cbf6e72]
DEBUG - Visiting attribute path : birthDate
DEBUG - Visiting attribute path : createdBy
DEBUG - Visiting attribute path : createdDate
DEBUG - Visiting attribute path : emailAddress
DEBUG - Visiting attribute path : firstName
DEBUG - Visiting attribute path : lastName
DEBUG - Visiting attribute path : lastUpdatedBy
DEBUG - Visiting attribute path : lastUpdatedDate
DEBUG - Building LoadPlan...
DEBUG - processing queryspace <gen:0>
DEBUG - LoadPlan(entity=com.company.app.model.User)
    - Returns
       - EntityReturnImpl(entity=com.company.app.model.User, querySpaceUid=<gen:0>, path=com.company.app.model.User)
    - QuerySpaces
       - EntityQuerySpaceImpl(uid=<gen:0>, entity=com.company.app.model.User)
          - SQL table alias mapping - user0_
          - alias suffix - 0_
          - suffixed key columns - {USER_ID1_0_0_}
 */
