package com.company.app;

import com.company.app.model.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209961.html

1, retrieving
2, modify entity
3, commit

  Bank bank = entityManager.find(Bank.class, 1L);
  bank.setName("Modified Bank");
  entityManager.getTransaction().commit();

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

      Bank bank = entityManager.find(Bank.class, 1L);
      bank.setName("Modified Bank");

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
Hibernate: select bank0_.BANK_ID as BANK_ID1_1_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_1_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_1_0_, bank0_.CITY as CITY4_1_0_, bank0_.STATE as STATE5_1_0_, bank0_.ZIP_CODE as ZIP_CODE6_1_0_, bank0_.CREATED_BY as CREATED_7_1_0_, bank0_.CREATED_DATE as CREATED_8_1_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_1_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_1_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_1_0_, bank0_.NAME as NAME12_1_0_ from bank bank0_ where bank0_.BANK_ID=?
Hibernate: update bank set ADDRESS_LINE_1=?, ADDRESS_LINE_2=?, CITY=?, STATE=?, ZIP_CODE=?, CREATED_BY=?, CREATED_DATE=?, IS_INTERNATIONAL=?, LAST_UPDATED_BY=?, LAST_UPDATED_DATE=?, NAME=? where BANK_ID=?
 */
