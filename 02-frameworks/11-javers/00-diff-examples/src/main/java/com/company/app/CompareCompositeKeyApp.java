package com.company.app;

import com.company.app.model.Currency;
import com.company.app.model.id.CurrencyId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompareCompositeKeyApp {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager1 = null;
    EntityManager entityManager2 = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("test-unit");
      entityManager1 = entityManagerFactory.createEntityManager();
      entityManager1.getTransaction().begin();

      Currency currency = new Currency();
      currency.setCountryName("China");
      currency.setName("CNY");
      currency.setSymbol("¥");

      entityManager1.persist(currency);

      entityManager1.getTransaction().commit();

      entityManager2 = entityManagerFactory.createEntityManager();
      entityManager2.getTransaction().begin();

      Currency dbCurrency = entityManager2.find(Currency.class, new CurrencyId("CNY", "China"));
      System.out.println(dbCurrency.getName());

      entityManager2.getTransaction().commit();

    }
    catch (Exception e){
      entityManager1.getTransaction().rollback();
      e.printStackTrace();
    }
    finally {
      entityManager1.close();
      entityManager2.close();
      entityManagerFactory.close();
    }
  }
}
