package com.company.app;

import com.company.app.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209979.html

> HQL - hibernate

- positional parameter - ?
  Query query = session.createQuery("select t from Transaction t where t.amount > ? and t.transactionType = 'Withdrawl'");
  System.out.println("Please specify an amount:");
  Scanner scanner = new Scanner(System.in);
  query.setParameter(0, new BigDecimal(scanner.next()));

- name parameter - :amount <- good one
    Query query = session.createQuery("select t from Transaction t where t.amount > :amount and t.transactionType = 'Withdrawl'");
    System.out.println("Please specify an amount:");
    Scanner scanner = new Scanner(System.in);
    query.setParameter("amount", new BigDecimal(scanner.next()));

run populate.sql before running this app

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

//    Query query = positionalParameters(session);

    Query query = nameParameters(session); // good one

    List<Transaction> transactions = query.list();

    transactions.forEach(t -> {
      System.out.println(t.getTitle());
    });

    session.getTransaction().commit();

    sessionFactory.close();
  }

  private static Query nameParameters(Session session) {
    Query query = session.createQuery("select t from Transaction t where t.amount > :amount and t.transactionType = 'Withdrawl'");
    System.out.println("Please specify an amount:");
    Scanner scanner = new Scanner(System.in);
    query.setParameter("amount", new BigDecimal(scanner.next()));
    return query;
  }

  private static Query positionalParameters(Session session) {
    Query query = session.createQuery("select t from Transaction t where t.amount > ? and t.transactionType = 'Withdrawl'");
    System.out.println("Please specify an amount:");
    Scanner scanner = new Scanner(System.in);
    query.setParameter(0, new BigDecimal(scanner.next()));
    return query;
  }
}
/*
Please specify an amount:
75
Hibernate: select transactio0_.TRANSACTION_ID as TRANSACT1_0_, transactio0_.AMOUNT as AMOUNT2_0_, transactio0_.CLOSING_BALANCE as CLOSING_3_0_, transactio0_.CREATED_BY as CREATED_4_0_, transactio0_.CREATED_DATE as CREATED_5_0_, transactio0_.INITIAL_BALANCE as INITIAL_6_0_, transactio0_.LAST_UPDATED_BY as LAST_UPD7_0_, transactio0_.LAST_UPDATED_DATE as LAST_UPD8_0_, transactio0_.NOTES as NOTES9_0_, transactio0_.TITLE as TITLE10_0_, transactio0_.TRANSACTION_TYPE as TRANSAC11_0_ from transaction transactio0_ where transactio0_.AMOUNT>? and transactio0_.TRANSACTION_TYPE='Withdrawl'
Work Shoes
Shirt
Pants
Groceries
 */
