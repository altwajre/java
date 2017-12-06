package com.company.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209981.html

> JPQL - function expression

http://docs.oracle.com/html/E13946_04/ejb3_langref.html#ejb3_langref_functional

> JPQL - jpa projection

- functions concat()
  "select distinct t.account.name,"
  + " concat(concat(t.account.bank.name, ' '),t.account.bank.address.state)"
  + " from Transaction t"
  + " where t.amount > 500 and t.transactionType = 'Deposit'");

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

      Query query = entityManager.createQuery(
          "select distinct t.account.name,"
          + " concat(concat(t.account.bank.name, ' '),t.account.bank.address.state)"
          + " from Transaction t"
          + " where t.amount > 500 and t.transactionType = 'Deposit'");

      List<Object[]> accounts = query.getResultList();

      accounts.forEach(a -> {
        System.out.println("t.account.name = " + a[0]);
        System.out.println("concat(concat(t.account.bank.name, ' '),t.account.address.state) = " + a[1]);
      });

      entityManager.getTransaction().commit();

    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      entityManager.close();
      entityManagerFactory.close();
    }

  }
}
/*
Hibernate: select distinct account1_.NAME as col_0_0_, concat(concat(bank3_.NAME, ' '), bank3_.STATE) as col_1_0_ from transaction transactio0_ cross join account account1_ cross join bank bank3_ where transactio0_.ACCOUNT_ID=account1_.ACCOUNT_ID and account1_.BANK_ID=bank3_.BANK_ID and transactio0_.AMOUNT>500 and transactio0_.TRANSACTION_TYPE='Deposit'
t.account.name = Checking Account
concat(concat(t.account.bank.name, ' '),t.account.address.state) = Second National Trust PA
 */
