package com.company.app;

import com.company.app.model.Account;
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
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209980.html

> JPQL - jpa

- explicit join - join keyword in the query
entityManager.createQuery(
          "select distinct a from Transaction t join t.account a where t.amount > 500 and t.transactionType = 'Deposit'",
          Account.class);

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

      TypedQuery<Account> query = entityManager.createQuery(
          "select distinct a from Transaction t join t.account a where t.amount > 500 and t.transactionType = 'Deposit'",
          Account.class);

      List<Account> accounts = query.getResultList();

      accounts.forEach(account -> {
        System.out.println(account.getName());
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
}
/*
Hibernate: select distinct account1_.ACCOUNT_ID as ACCOUNT_1_0_, account1_.CLOSE_DATE as CLOSE_DA2_0_, account1_.CREATED_BY as CREATED_3_0_, account1_.CREATED_DATE as CREATED_4_0_, account1_.CURRENT_BALANCE as CURRENT_5_0_, account1_.INITIAL_BALANCE as INITIAL_6_0_, account1_.LAST_UPDATED_BY as LAST_UPD7_0_, account1_.LAST_UPDATED_DATE as LAST_UPD8_0_, account1_.NAME as NAME9_0_, account1_.OPEN_DATE as OPEN_DA10_0_ from transaction transactio0_ inner join account account1_ on transactio0_.ACCOUNT_ID=account1_.ACCOUNT_ID where transactio0_.AMOUNT>500 and transactio0_.TRANSACTION_TYPE='Deposit'
Checking Account
 */
