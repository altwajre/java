package com.company.app;

import com.company.app.model.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209959.html

entityManager.persist(bank);

> config

resources/META-INF/persistence.xml

> SQL

run InfiniteFinancesSchema.sql and populate.sql to populate tables

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Bank bank = new Bank();
      bank.setName("EntityManager Federal");
      bank.setAddressLine1("1124 Pine st");
      bank.setAddressLine2("Apt 130");
      bank.setCity("San Francisco");
      bank.setCreatedBy("Tom Leee");
      bank.setCreatedDate(new Date());
      bank.setInternational(false);
      bank.setLastUpdatedBy("Tom Lee");
      bank.setLastUpdatedDate(new Date());
      bank.setState("SF");
      bank.setZipCode("18000");

      entityManager.persist(bank);

      entityManager.getTransaction().commit();
    }
    catch (Exception e){
      entityManager.getTransaction().rollback();
      e.printStackTrace();
    }
    finally {
      entityManager.close();
      entityManagerFactory.close();
    }
  }
}
/*
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into bank (ADDRESS_LINE_1, ADDRESS_LINE_2, CITY, STATE, ZIP_CODE, CREATED_BY, CREATED_DATE, IS_INTERNATIONAL, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, BANK_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
 */
