package com.company.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

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

Query query = entityManager.createNamedQuery("Account.byWithdrawlAmount");
query.setParameter("amount", new BigDecimal("99"));

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
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Query query = entityManager.createNamedQuery("Account.byWithdrawlAmount");
      query.setParameter("amount", new BigDecimal("99"));

      List<Object[]> accounts = query.getResultList();

      accounts.forEach(a -> {
        System.out.println("t.account.name = " + a[0]);
        System.out.println("concat(concat(t.account.bank.name, ' '),t.account.address.state) = " + a[1]);
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
Hibernate: select distinct account1_.NAME as col_0_0_, concat(concat(bank3_.NAME, ' '), bank3_.STATE) as col_1_0_ from transaction transactio0_ cross join account account1_ cross join bank bank3_ where transactio0_.ACCOUNT_ID=account1_.ACCOUNT_ID and account1_.BANK_ID=bank3_.BANK_ID and transactio0_.AMOUNT>? and transactio0_.TRANSACTION_TYPE='withdrawl'
t.account.name = Checking Account
concat(concat(t.account.bank.name, ' '),t.account.address.state) = Second National Trust PA
 */
