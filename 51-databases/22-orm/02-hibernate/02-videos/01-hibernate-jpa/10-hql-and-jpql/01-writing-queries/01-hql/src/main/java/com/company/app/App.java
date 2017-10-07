package com.company.app;

import com.company.app.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209978.html

HQL - hibernate

run populate.sql before running this app

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Query query = session.createQuery("select t from Transaction t");
    List<Transaction> transactions = query.list();

    transactions.forEach(t -> {
      System.out.println(t.getTitle());
    });

    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: select transactio0_.TRANSACTION_ID as TRANSACT1_0_, transactio0_.AMOUNT as AMOUNT2_0_, transactio0_.CLOSING_BALANCE as CLOSING_3_0_, transactio0_.CREATED_BY as CREATED_4_0_, transactio0_.CREATED_DATE as CREATED_5_0_, transactio0_.INITIAL_BALANCE as INITIAL_6_0_, transactio0_.LAST_UPDATED_BY as LAST_UPD7_0_, transactio0_.LAST_UPDATED_DATE as LAST_UPD8_0_, transactio0_.NOTES as NOTES9_0_, transactio0_.TITLE as TITLE10_0_, transactio0_.TRANSACTION_TYPE as TRANSAC11_0_ from transaction transactio0_
Dress Belt
Work Shoes
Shirt
Socks
Tie
Pants
Lunch
Dinner
Breakfast
Groceries
Pay Check
Bonus
 */
