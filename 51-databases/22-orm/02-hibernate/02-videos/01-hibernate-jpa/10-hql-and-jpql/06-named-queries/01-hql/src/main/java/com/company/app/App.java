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
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209982.html

> HQL - hibernate `named queries`

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

//    Query query = session.createQuery(
//        "select distinct t.account from Transaction t where t.amount > 500 and lower(t.transactionType) = 'deposit'");

    Query query = session.getNamedQuery("Account.largeDeposits");

    List<Account> accounts = query.list();

    accounts.forEach(t -> {
      System.out.println(t.getName());
    });

    session.getTransaction().commit();

    sessionFactory.close();
  }
}
/*
Hibernate: select distinct account1_.ACCOUNT_ID as ACCOUNT_1_0_, account1_.CLOSE_DATE as CLOSE_DA2_0_, account1_.CREATED_BY as CREATED_3_0_, account1_.CREATED_DATE as CREATED_4_0_, account1_.CURRENT_BALANCE as CURRENT_5_0_, account1_.INITIAL_BALANCE as INITIAL_6_0_, account1_.LAST_UPDATED_BY as LAST_UPD7_0_, account1_.LAST_UPDATED_DATE as LAST_UPD8_0_, account1_.NAME as NAME9_0_, account1_.OPEN_DATE as OPEN_DA10_0_ from transaction transactio0_ inner join account account1_ on transactio0_.ACCOUNT_ID=account1_.ACCOUNT_ID where transactio0_.AMOUNT>500 and transactio0_.TRANSACTION_TYPE='Deposit'
Checking Account
 */
