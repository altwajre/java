package com.company.app;

import com.company.app.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209978.html

> JPQL - jpa

- positional parameter - ?1
    TypedQuery<Transaction> query = entityManager.createQuery(
        "from Transaction t where (t.amount between ?1 and ?2) and t.title like '%s' order by t.title",
        Transaction.class);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the first amount");
    query.setParameter(1, new BigDecimal(scanner.next()));
    System.out.println("Please provide the second amount");
    query.setParameter(2, new BigDecimal(scanner.next()));

- name parameter - :amount <- good one
    TypedQuery<Transaction> query = entityManager.createQuery(
        "from Transaction t where (t.amount between ?1 and ?2) and t.title like '%s' order by t.title",
        Transaction.class);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the first amount");
    query.setParameter(1, new BigDecimal(scanner.next()));
    System.out.println("Please provide the second amount");
    query.setParameter(2, new BigDecimal(scanner.next()));

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

//      TypedQuery<Transaction> query = positionalParameters(entityManager);

      TypedQuery<Transaction> query = nameParameters(entityManager); // good one

      List<Transaction> transactions = query.getResultList();

      transactions.forEach(t -> {
        System.out.println(t.getTitle());
      });

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

  private static TypedQuery<Transaction> nameParameters(EntityManager entityManager) {
    TypedQuery<Transaction> query = entityManager.createQuery(
        "from Transaction t where (t.amount between :amount1 and :amount2) and t.title like '%s' order by t.title",
        Transaction.class);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the first amount");
    query.setParameter("amount1", new BigDecimal(scanner.next()));
    System.out.println("Please provide the second amount");
    query.setParameter("amount2", new BigDecimal(scanner.next()));
    return query;
  }

  private static TypedQuery<Transaction> positionalParameters(EntityManager entityManager) {
    TypedQuery<Transaction> query = entityManager.createQuery(
        "from Transaction t where (t.amount between ?1 and ?2) and t.title like '%s' order by t.title",
        Transaction.class);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the first amount");
    query.setParameter(1, new BigDecimal(scanner.next()));
    System.out.println("Please provide the second amount");
    query.setParameter(2, new BigDecimal(scanner.next()));
    return query;
  }

}
/*
Please provide the first amount
75
Please provide the second amount
100
Hibernate: select transactio0_.TRANSACTION_ID as TRANSACT1_0_, transactio0_.AMOUNT as AMOUNT2_0_, transactio0_.CLOSING_BALANCE as CLOSING_3_0_, transactio0_.CREATED_BY as CREATED_4_0_, transactio0_.CREATED_DATE as CREATED_5_0_, transactio0_.INITIAL_BALANCE as INITIAL_6_0_, transactio0_.LAST_UPDATED_BY as LAST_UPD7_0_, transactio0_.LAST_UPDATED_DATE as LAST_UPD8_0_, transactio0_.NOTES as NOTES9_0_, transactio0_.TITLE as TITLE10_0_, transactio0_.TRANSACTION_TYPE as TRANSAC11_0_ from transaction transactio0_ where (transactio0_.AMOUNT between ? and ?) and (transactio0_.TITLE like '%s') order by transactio0_.TITLE
Groceries
Pants
Work Shoes
 */
