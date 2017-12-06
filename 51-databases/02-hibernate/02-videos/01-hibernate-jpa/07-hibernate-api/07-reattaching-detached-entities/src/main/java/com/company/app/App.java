package com.company.app;

import com.company.app.model.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209954.html

run populate.sql to ensure the record is there before running the app

1, get bank entity into session1 persistent context from database
2, detach bank entity - session1.close()
3, reattach bank entity into the session2 persistent context - session2.update()
 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();

    final Session session1 = sessionFactory.openSession();
    session1.getTransaction().begin();
    Bank bank = session1.get(Bank.class, 1L); // get bank entity into session1 persistent context from database
    session1.getTransaction().commit();
    session1.close(); // detach bank entity

    Session session2 = sessionFactory.openSession();
    session2.getTransaction().begin();

    System.out.println("#Before reattach bank entity");
    System.out.println("session2.contains(bank)=" + session2.contains(bank));

    session2.update(bank); // reattach bank entity
    bank.setName("Test Bank");

    System.out.println("session2.update(bank) invoked");
    System.out.println("#After reattach bank entity");
    System.out.println("session2.contains(bank)=" + session2.contains(bank));

    session2.getTransaction().commit();
    session2.close();
    sessionFactory.close();
  }
}
/*
Hibernate: select bank0_.BANK_ID as BANK_ID1_0_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_0_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_0_0_, bank0_.CITY as CITY4_0_0_, bank0_.STATE as STATE5_0_0_, bank0_.ZIP_CODE as ZIP_CODE6_0_0_, bank0_.CREATED_BY as CREATED_7_0_0_, bank0_.CREATED_DATE as CREATED_8_0_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_0_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_0_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_0_0_, bank0_.NAME as NAME12_0_0_ from bank bank0_ where bank0_.BANK_ID=?
#Before reattach bank entity
session2.contains(bank)=false
session2.update(bank) invoked
#After reattach bank entity
session2.contains(bank)=true
Hibernate: update bank set ADDRESS_LINE_1=?, ADDRESS_LINE_2=?, CITY=?, STATE=?, ZIP_CODE=?, CREATED_BY=?, CREATED_DATE=?, IS_INTERNATIONAL=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=?, NAME=? where BANK_ID=?
 */
