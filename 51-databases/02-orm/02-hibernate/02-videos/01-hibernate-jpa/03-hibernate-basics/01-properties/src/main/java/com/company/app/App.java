package com.company.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209920.html

resources/hibernate.properties
hibernate.connection.username=infinite
hibernate.connection.password=skills
hibernate.connection.url=jdbc:mysql://localhost:3306/ifinances
hibernate.connection.driver_class=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

run InfiniteFinancesSchema.sql first before running this app
 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.beginTransaction();
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
