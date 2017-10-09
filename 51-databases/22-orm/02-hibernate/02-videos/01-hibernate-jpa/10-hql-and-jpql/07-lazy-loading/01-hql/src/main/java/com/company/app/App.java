package com.company.app;

import com.company.app.model.Account;
import com.company.app.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209983.html

> HQL - hibernate `lazy loading` avoid execute not needed SQLs

Account.java
@Entity
@Table(name = "account")
@NamedQueries({
	@NamedQuery(name="Account.largeDeposits", query="select distinct t.account from Transaction t"
				+ " where t.amount > 500 and lower(t.transactionType) = 'deposit'"),
	@NamedQuery(name="Account.byWithdrawlAmount", query="select distinct t.account.name, "
					+ "concat(concat(t.account.bank.name, ' '),t.account.bank.address.state)"
					+ " from Transaction t"
					+ " where t.amount > :amount and t.transactionType = 'withdrawl'")
})
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BANK_ID")
  private Bank bank;

App.java
Query query = session.getNamedQuery("Account.largeDeposits");

> Config

public enum SessionFactoryBuilder {

  INSTANCE;

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      Configuration configuration = new Configuration();
      configuration.addAnnotatedClass(Account.class);
      configuration.addAnnotatedClass(Transaction.class);
      configuration.addAnnotatedClass(Bank.class);
      configuration.addAnnotatedClass(Address.class);

-----

run populate.sql before running this app

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Query query = session.getNamedQuery("Account.largeDeposits");
    List<Account> accounts = query.list();
    System.out.println("Query has been executed.");

    accounts.forEach(a -> {
      System.out.println(a.getName());
      System.out.println(a.getBank().getName()); // execute `select bank` when needing the bank
    });

    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: select distinct account1_.ACCOUNT_ID as ACCOUNT_1_0_, account1_.BANK_ID as BANK_ID11_0_, account1_.CLOSE_DATE as CLOSE_DA2_0_, account1_.CREATED_BY as CREATED_3_0_, account1_.CREATED_DATE as CREATED_4_0_, account1_.CURRENT_BALANCE as CURRENT_5_0_, account1_.INITIAL_BALANCE as INITIAL_6_0_, account1_.LAST_UPDATED_BY as LAST_UPD7_0_, account1_.LAST_UPDATED_DATE as LAST_UPD8_0_, account1_.NAME as NAME9_0_, account1_.OPEN_DATE as OPEN_DA10_0_ from transaction transactio0_ inner join account account1_ on transactio0_.ACCOUNT_ID=account1_.ACCOUNT_ID where transactio0_.AMOUNT>500 and lower(transactio0_.TRANSACTION_TYPE)='deposit'
Query has been executed.
Checking Account
Hibernate: select bank0_.BANK_ID as BANK_ID1_1_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_1_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_1_0_, bank0_.ADDRESS_TYPE as ADDRESS_4_1_0_, bank0_.CITY as CITY5_1_0_, bank0_.STATE as STATE6_1_0_, bank0_.ZIP_CODE as ZIP_CODE7_1_0_, bank0_.CREATED_BY as CREATED_8_1_0_, bank0_.CREATED_DATE as CREATED_9_1_0_, bank0_.IS_INTERNATIONAL as IS_INTE10_1_0_, bank0_.LAST_UPDATED_BY as LAST_UP11_1_0_, bank0_.LAST_UPDATED_DATE as LAST_UP12_1_0_, bank0_.NAME as NAME13_1_0_ from bank bank0_ where bank0_.BANK_ID=?
Second National Trust
 */
