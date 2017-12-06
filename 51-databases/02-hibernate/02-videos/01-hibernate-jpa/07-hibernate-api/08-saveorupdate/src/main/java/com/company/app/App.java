package com.company.app;

import com.company.app.model.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209955.html

session2.saveOrUpdate(transientBank); // insert a new bank record
session2.saveOrUpdate(detachedBank); // update reattach bank entity

run populate.sql to ensure the record is there before running the app

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();

    final Session session1 = sessionFactory.openSession();
    session1.getTransaction().begin();
    Bank detachedBank = session1.get(Bank.class, 1L); // get bank entity into session1 persistent context from database
    session1.getTransaction().commit();
    session1.close(); // detach bank entity

    Bank transientBank = new Bank();
    transientBank.setName("Transient Bank");
    transientBank.setAddressLine1("1124 Pne");
    transientBank.setAddressLine2("Apt 332");
    transientBank.setCity("San Francisco");
    transientBank.setCreatedBy("Tom Lee");
    transientBank.setCreatedDate(new Date());
    transientBank.setInternational(false);
    transientBank.setLastUpdatedBy("Tom Lee");
    transientBank.setLastUpdatedDate(new Date());
    transientBank.setState("SF");
    transientBank.setZipCode("18000");

    Session session2 = sessionFactory.openSession();
    session2.getTransaction().begin();

    session2.saveOrUpdate(transientBank); // persist transient entity (a new bank record)
    session2.saveOrUpdate(detachedBank); // update reattach bank entity
    detachedBank.setName("Test Bank");

    session2.getTransaction().commit();
    session2.close();
    sessionFactory.close();
  }
}
/*
Hibernate: select bank0_.BANK_ID as BANK_ID1_0_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_0_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_0_0_, bank0_.CITY as CITY4_0_0_, bank0_.STATE as STATE5_0_0_, bank0_.ZIP_CODE as ZIP_CODE6_0_0_, bank0_.CREATED_BY as CREATED_7_0_0_, bank0_.CREATED_DATE as CREATED_8_0_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_0_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_0_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_0_0_, bank0_.NAME as NAME12_0_0_ from bank bank0_ where bank0_.BANK_ID=?
Tue Oct 03 16:17:19 PDT 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into bank (ADDRESS_LINE_1, ADDRESS_LINE_2, CITY, STATE, ZIP_CODE, CREATED_BY, CREATED_DATE, IS_INTERNATIONAL, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, BANK_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: update bank set ADDRESS_LINE_1=?, ADDRESS_LINE_2=?, CITY=?, STATE=?, ZIP_CODE=?, CREATED_BY=?, CREATED_DATE=?, IS_INTERNATIONAL=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=?, NAME=? where BANK_ID=?
 */
