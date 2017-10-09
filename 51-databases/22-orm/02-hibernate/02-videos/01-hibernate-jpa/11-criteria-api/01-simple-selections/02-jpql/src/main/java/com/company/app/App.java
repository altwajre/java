package com.company.app;

import com.company.app.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209985.html?autoStart=True

> JPQL - JPA api `simple selections`

CriteriaBuilder builder = entityManager.getCriteriaBuilder();
CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
Root<Transaction> root = query.from(Transaction.class);
query.orderBy(builder.desc(root.get("title")));
List<Transaction> transactions = entityManager.createQuery(query).getResultList();

-----

run populate.sql before running this app

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
      Root<Transaction> root = query.from(Transaction.class);
      query.orderBy(builder.desc(root.get("title")));
      List<Transaction> transactions = entityManager.createQuery(query).getResultList();

      transactions.forEach(t -> {
        System.out.println(t.getTitle());
      });

      entityManager.getTransaction().commit();

    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    } finally {
      entityManager.close();
      entityManagerFactory.close();
    }

  }
}
/*
Hibernate: select transactio0_.TRANSACTION_ID as TRANSACT1_3_, transactio0_.ACCOUNT_ID as ACCOUNT12_3_, transactio0_.AMOUNT as AMOUNT2_3_, transactio0_.CLOSING_BALANCE as CLOSING_3_3_, transactio0_.CREATED_BY as CREATED_4_3_, transactio0_.CREATED_DATE as CREATED_5_3_, transactio0_.INITIAL_BALANCE as INITIAL_6_3_, transactio0_.LAST_UPDATED_BY as LAST_UPD7_3_, transactio0_.LAST_UPDATED_DATE as LAST_UPD8_3_, transactio0_.NOTES as NOTES9_3_, transactio0_.TITLE as TITLE10_3_, transactio0_.TRANSACTION_TYPE as TRANSAC11_3_ from transaction transactio0_ order by transactio0_.TITLE desc
Hibernate: select account0_.ACCOUNT_ID as ACCOUNT_1_0_0_, account0_.BANK_ID as BANK_ID11_0_0_, account0_.CLOSE_DATE as CLOSE_DA2_0_0_, account0_.CREATED_BY as CREATED_3_0_0_, account0_.CREATED_DATE as CREATED_4_0_0_, account0_.CURRENT_BALANCE as CURRENT_5_0_0_, account0_.INITIAL_BALANCE as INITIAL_6_0_0_, account0_.LAST_UPDATED_BY as LAST_UPD7_0_0_, account0_.LAST_UPDATED_DATE as LAST_UPD8_0_0_, account0_.NAME as NAME9_0_0_, account0_.OPEN_DATE as OPEN_DA10_0_0_ from account account0_ where account0_.ACCOUNT_ID=?
Work Shoes
Tie
Socks
Shirt
Pay Check
Pants
Lunch
Groceries
Dress Belt
Dinner
Breakfast
Bonus
 */
