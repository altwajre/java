package com.company.app;

import com.company.app.model.Account;
import com.company.app.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209950.html

Transient state to Persistent state by using session.save()
 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Account account = new Account(); // transient entity
    account.setCloseDate(new Date());
    account.setOpenDate(new Date());
    account.setCreatedBy("Kevin Bowersox");
    account.setInitialBalance(new BigDecimal("50.00"));
    account.setName("Savings Account");
    account.setCurrentBalance(new BigDecimal("100.00"));
    account.setLastUpdatedBy("Kevin Bowersox");
    account.setLastUpdatedDate(new Date());
    account.setCreatedDate(new Date());

    Transaction beltPurchase = new Transaction();
    beltPurchase.setTitle("Dress Belt");
    beltPurchase.setAmount(new BigDecimal("50.00"));
    beltPurchase.setClosingBalance(new BigDecimal("0.00"));
    beltPurchase.setCreatedBy("Kevin Bowersox");
    beltPurchase.setCreatedDate(new Date());
    beltPurchase.setInitialBalance(new BigDecimal("0.00"));
    beltPurchase.setLastUpdatedBy("Kevin Bowersox");
    beltPurchase.setLastUpdatedDate(new Date());
    beltPurchase.setNotes("New Dress Belt");
    beltPurchase.setTransactionType("Debit");

    account.getTransactions().add(beltPurchase);

    Transaction shoePurchase = new Transaction();
    shoePurchase.setTitle("Work Shoes");
    shoePurchase.setAmount(new BigDecimal("100.00"));
    shoePurchase.setClosingBalance(new BigDecimal("0.00"));
    shoePurchase.setCreatedBy("Kevin Bowersox");
    shoePurchase.setCreatedDate(new Date());
    shoePurchase.setInitialBalance(new BigDecimal("0.00"));
    shoePurchase.setLastUpdatedBy("Kevin Bowersox");
    shoePurchase.setLastUpdatedDate(new Date());
    shoePurchase.setNotes("Nice Pair of Shoes");
    shoePurchase.setTransactionType("Debit");

    account.getTransactions().add(shoePurchase);

    System.out.println("#Before save() - Transient state");
    System.out.println("session.contains(account)=" + session.contains(account));
    System.out.println("session.contains(beltPurchase)=" + session.contains(beltPurchase));
    System.out.println("session.contains(shoePurchase)" + session.contains(shoePurchase));

    session.save(account); // calling save() transition `account` from Transient to Persistent state

    System.out.println("#After save() - Persistent state");
    System.out.println("session.contains(account)=" + session.contains(account));
    System.out.println("session.contains(beltPurchase)=" + session.contains(beltPurchase));
    System.out.println("session.contains(shoePurchase)" + session.contains(shoePurchase));

    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
#Before save() - Transient state
session.contains(account)=false
session.contains(beltPurchase)=false
session.contains(shoePurchase)false
Tue Oct 03 11:18:06 PDT 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into account (CLOSE_DATE, CREATED_BY, CREATED_DATE, CURRENT_BALANCE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NAME, OPEN_DATE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into transaction (AMOUNT, CLOSING_BALANCE, CREATED_BY, CREATED_DATE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NOTES, TITLE, TRANSACTION_TYPE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into transaction (AMOUNT, CLOSING_BALANCE, CREATED_BY, CREATED_DATE, INITIAL_BALANCE, LAST_UPDATED_BY, LAST_UPDATED_DATE, NOTES, TITLE, TRANSACTION_TYPE, ACCOUNT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
#After save() - Persistent state
session.contains(account)=true
session.contains(beltPurchase)=true
session.contains(shoePurchase)true
Hibernate: update transaction set ACCOUNT_ID=? where TRANSACTION_ID=?
Hibernate: update transaction set ACCOUNT_ID=? where TRANSACTION_ID=?
 */
