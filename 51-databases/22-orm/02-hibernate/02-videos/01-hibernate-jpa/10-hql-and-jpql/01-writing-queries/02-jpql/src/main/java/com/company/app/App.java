package com.company.app;

import com.company.app.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209978.html

JPQL - jpa

run populate.sql before running this app

 */

public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

//      query(entityManager);

      typedQuery(entityManager);

      entityManager.getTransaction().commit();

    }
    catch (Exception e){
      entityManager.getTransaction().rollback();
    }
    finally {
      entityManager.close();
      entityManagerFactory.close();
    }

  }

  private static void typedQuery(EntityManager entityManager) {
    TypedQuery<Transaction> query = entityManager.createQuery("from Transaction t order by t.title", Transaction.class);
    List<Transaction> transactions = query.getResultList();

    transactions.forEach(t -> {
      System.out.println(t.getTitle());
    });
  }

  private static void query(EntityManager entityManager) {
    Query query = entityManager.createQuery("from Transaction t order by t.title");
    List<Transaction> transactions = query.getResultList();

    transactions.forEach(t -> {
      System.out.println(t.getTitle());
    });
  }
}
/*
Hibernate: select transactio0_.TRANSACTION_ID as TRANSACT1_0_, transactio0_.AMOUNT as AMOUNT2_0_, transactio0_.CLOSING_BALANCE as CLOSING_3_0_, transactio0_.CREATED_BY as CREATED_4_0_, transactio0_.CREATED_DATE as CREATED_5_0_, transactio0_.INITIAL_BALANCE as INITIAL_6_0_, transactio0_.LAST_UPDATED_BY as LAST_UPD7_0_, transactio0_.LAST_UPDATED_DATE as LAST_UPD8_0_, transactio0_.NOTES as NOTES9_0_, transactio0_.TITLE as TITLE10_0_, transactio0_.TRANSACTION_TYPE as TRANSAC11_0_ from transaction transactio0_ order by transactio0_.TITLE
Bonus
Breakfast
Dinner
Dress Belt
Groceries
Lunch
Pants
Pay Check
Shirt
Socks
Tie
Work Shoes
 */
