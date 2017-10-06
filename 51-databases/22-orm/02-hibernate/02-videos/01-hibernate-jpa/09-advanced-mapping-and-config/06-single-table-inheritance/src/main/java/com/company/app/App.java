package com.company.app;

import com.company.app.model.Bond;
import com.company.app.model.Portfolio;
import com.company.app.model.Stock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209972.html

> SQL tables

put both Bond and Stock into `investment` table

describe investment;
+-----------------+---------------+------+-----+---------+----------------+
| Field           | Type          | Null | Key | Default | Extra          |
+-----------------+---------------+------+-----+---------+----------------+
| INVESTMENT_ID   | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| PORTFOLIO_ID    | bigint(20)    | YES  |     | NULL    |                |
| NAME            | varchar(100)  | YES  |     | NULL    |                |
| ISSUER          | varchar(100)  | YES  |     | NULL    |                |
| PURCHASE_DATE   | datetime      | YES  |     | NULL    |                |
| VALUE           | decimal(10,2) | YES  |     | NULL    |                |
| INTEREST_RATE   | decimal(10,2) | YES  |     | NULL    |                |
| MATURITY_DATE   | datetime      | YES  |     | NULL    |                |
| SHARE_PRICE     | decimal(10,2) | YES  |     | NULL    |                |
| QUANTITY        | bigint(20)    | YES  |     | NULL    |                |
| INVESTMENT_TYPE | varchar(100)  | YES  |     | NULL    |                |
+-----------------+---------------+------+-----+---------+----------------+

CREATE TABLE `investment` (
  `INVESTMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ISSUER` varchar(100) DEFAULT NULL,
  `PURCHASE_DATE` datetime DEFAULT NULL,
  `VALUE` decimal(10,2) DEFAULT NULL,
  `INTEREST_RATE` decimal(10,2) DEFAULT NULL,
  `MATURITY_DATE` datetime DEFAULT NULL,
  `SHARE_PRICE` decimal(10,2) DEFAULT NULL,
  `QUANTITY` bigint(20) DEFAULT NULL,
  `INVESTMENT_TYPE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`)
)

> Object model

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INVESTMENT_TYPE")
@Table(name="investment")
public abstract class Investment {

  @Id
  @GeneratedValue
  @Column(name="INVESTMENT_ID")
  private Long investmentId;

  @JoinColumn(name="PORTFOLIO_ID")
  @ManyToOne(cascade=CascadeType.ALL)
  private Portfolio portfolio;

  @Column(name = "NAME")
  protected String name;

  @Column(name = "ISSUER")
  protected String issuer;

  @Column(name = "PURCHASE_DATE")
  protected Date purchaseDate;
}

@Data
@Entity
@DiscriminatorValue("BND")
public class Bond extends Investment{

  @Column(name = "VALUE")
  private BigDecimal value;

  @Column(name = "INTEREST_RATE")
  private BigDecimal interestRate;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "MATURITY_DATE")
  private Date maturityDate;
}

@Data
@Entity
@DiscriminatorValue("ST")
public class Stock extends Investment {

  @Column(name = "SHARE_PRICE")
  private BigDecimal sharePrice;

  @Column(name = "QUANTITY")
  private BigDecimal quantity;
}

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Portfolio portfolio = new Portfolio();
      portfolio.setName("Dick Firm");

      Stock stock = new Stock();
      stock.setIssuer("Allen Edmonds");
      stock.setName("Private American Stock Purchases");
      stock.setPurchaseDate(new Date());
      stock.setQuantity(new BigDecimal("1922"));
      stock.setSharePrice(new BigDecimal("100.00"));
      stock.setPortfolio(portfolio);

      Bond bond = new Bond();
      bond.setInterestRate(new BigDecimal("123.22"));
      bond.setIssuer("JP Morgan Chase");
      bond.setMaturityDate(new Date());
      bond.setPurchaseDate(new Date());
      bond.setName("Long Term Bond Purchases");
      bond.setValue(new BigDecimal("10.22"));
      bond.setPortfolio(portfolio);

      entityManager.persist(stock);
      entityManager.persist(bond);

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
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into portfolio (NAME, PORTFOLIO_ID) values (?, ?)
Hibernate: insert into investment (ISSUER, NAME, PORTFOLIO_ID, PURCHASE_DATE, QUANTITY, SHARE_PRICE, INVESTMENT_TYPE, INVESTMENT_ID) values (?, ?, ?, ?, ?, ?, 'ST', ?)
Hibernate: insert into investment (ISSUER, NAME, PORTFOLIO_ID, PURCHASE_DATE, INTEREST_RATE, MATURITY_DATE, VALUE, INVESTMENT_TYPE, INVESTMENT_ID) values (?, ?, ?, ?, ?, ?, ?, 'BND', ?)
 */
