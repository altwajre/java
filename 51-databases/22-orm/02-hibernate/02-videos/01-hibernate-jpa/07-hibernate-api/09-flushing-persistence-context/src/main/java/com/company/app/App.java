package com.company.app;

import com.company.app.model.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209956.html

session.flush(); takes whatever in persistence context and sync them with the underline database

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();

    try {
      session.getTransaction().begin();

      Bank bank = session.get(Bank.class, 1L);
      bank.setName("Something Different");
      System.out.println("Calling Flush");
      session.flush();

      bank.setAddressLine1("Another Address Line");
      System.out.println("Calling commit");
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      sessionFactory.close();
    }
  }
}
/*
Hibernate: select bank0_.BANK_ID as BANK_ID1_0_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_0_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_0_0_, bank0_.CITY as CITY4_0_0_, bank0_.STATE as STATE5_0_0_, bank0_.ZIP_CODE as ZIP_CODE6_0_0_, bank0_.CREATED_BY as CREATED_7_0_0_, bank0_.CREATED_DATE as CREATED_8_0_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_0_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_0_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_0_0_, bank0_.NAME as NAME12_0_0_ from bank bank0_ where bank0_.BANK_ID=?
Calling Flush
Hibernate: update bank set ADDRESS_LINE_1=?, ADDRESS_LINE_2=?, CITY=?, STATE=?, ZIP_CODE=?, CREATED_BY=?, CREATED_DATE=?, IS_INTERNATIONAL=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=?, NAME=? where BANK_ID=?
Calling commit
Hibernate: update bank set ADDRESS_LINE_1=?, ADDRESS_LINE_2=?, CITY=?, STATE=?, ZIP_CODE=?, CREATED_BY=?, CREATED_DATE=?, IS_INTERNATIONAL=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=?, NAME=? where BANK_ID=?
 */
