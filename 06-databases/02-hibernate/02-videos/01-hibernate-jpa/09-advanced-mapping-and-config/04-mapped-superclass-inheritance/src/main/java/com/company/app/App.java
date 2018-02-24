package com.company.app;

import com.company.app.model.Bond;
import com.company.app.model.Stock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209969.html

class inheritance - move the common fields to the base class
each table contains the common fields

> SQL tables

describe stock;
+---------------+---------------+------+-----+---------+----------------+
| Field         | Type          | Null | Key | Default | Extra          |
+---------------+---------------+------+-----+---------+----------------+
| STOCK_ID      | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| NAME          | varchar(100)  | NO   |     | NULL    |                |
| ISSUER        | varchar(45)   | NO   |     | NULL    |                |
| PURCHASE_DATE | datetime      | NO   |     | NULL    |                |
| SHARE_PRICE   | decimal(10,2) | NO   |     | NULL    |                |
| QUANTITY      | bigint(20)    | NO   |     | NULL    |                |
| PORTFOLIO_ID  | bigint(20)    | YES  | MUL | NULL    |                |
+---------------+---------------+------+-----+---------+----------------+

CREATE TABLE `stock` (
  `STOCK_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ISSUER` varchar(45) NOT NULL,
  `PURCHASE_DATE` datetime NOT NULL,
  `SHARE_PRICE` decimal(10,2) NOT NULL,
  `QUANTITY` bigint(20) NOT NULL,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`STOCK_ID`),
  KEY `PORTFOLIO_FK_idx` (`PORTFOLIO_ID`),
  CONSTRAINT `PORTFOLIO_FK` FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `portfolio` (`PORTFOLIO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

describe bond;
+---------------+---------------+------+-----+---------+----------------+
| Field         | Type          | Null | Key | Default | Extra          |
+---------------+---------------+------+-----+---------+----------------+
| BOND_ID       | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| NAME          | varchar(100)  | NO   |     | NULL    |                |
| ISSUER        | varchar(100)  | NO   |     | NULL    |                |
| PURCHASE_DATE | datetime      | NO   |     | NULL    |                |
| VALUE         | decimal(10,2) | NO   |     | NULL    |                |
| INTEREST_RATE | decimal(10,2) | NO   |     | NULL    |                |
| MATURITY_DATE | datetime      | NO   |     | NULL    |                |
| PORTFOLIO_ID  | bigint(20)    | YES  | MUL | NULL    |                |
+---------------+---------------+------+-----+---------+----------------+

CREATE TABLE `bond` (
  `BOND_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ISSUER` varchar(100) NOT NULL,
  `PURCHASE_DATE` datetime NOT NULL,
  `VALUE` decimal(10,2) NOT NULL,
  `INTEREST_RATE` decimal(10,2) NOT NULL,
  `MATURITY_DATE` datetime NOT NULL,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`BOND_ID`),
  KEY `PORTFOLIO_FK2_idx` (`PORTFOLIO_ID`),
  CONSTRAINT `PORTFOLIO_FK2` FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `portfolio` (`PORTFOLIO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

> Object model

@Data
@MappedSuperclass
public abstract class Investment {
  @Column(name = "NAME")
  protected String name;

  @Column(name = "ISSUER")
  protected String issuer;

  @Column(name = "PURCHASE_DATE")
  protected Date purchaseDate;
}

@Data
@Entity
@Table(name = "STOCK")
public class Stock extends Investment {

  @Id
  @GeneratedValue
  @Column(name = "STOCK_ID")
  private Long stockId;

  @Column(name = "SHARE_PRICE")
  private BigDecimal sharePrice;

  @Column(name = "QUANTITY")
  private BigDecimal quantity;
}

@Data
@Entity
@Table(name = "BOND")
public class Bond extends Investment{

  @Id
  @GeneratedValue
  @Column(name = "BOND_ID")
  private Long bondId;

  @Column(name = "VALUE")
  private BigDecimal value;

  @Column(name = "INTEREST_RATE")
  private BigDecimal interestRate;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "MATURITY_DATE")
  private Date maturityDate;
}

-----

run InfiniteFinancesSchema.sql first before running this app

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Stock stock = new Stock();
      stock.setIssuer("Allen Edmonds");
      stock.setName("Private American Stock Purchases");
      stock.setPurchaseDate(new Date());
      stock.setQuantity(new BigDecimal("1922"));
      stock.setSharePrice(new BigDecimal("100.00"));
      entityManager.persist(stock);

      Bond bond = new Bond();
      bond.setInterestRate(new BigDecimal("123.22"));
      bond.setIssuer("JP Morgan Chase");
      bond.setMaturityDate(new Date());
      bond.setPurchaseDate(new Date());
      bond.setName("Long Term Bond Purchases");
      bond.setValue(new BigDecimal("10.22"));
      entityManager.persist(bond);

      entityManager.getTransaction().commit();

    }
    catch (Exception e){
      entityManager.getTransaction().rollback();
      e.printStackTrace();
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
Hibernate: insert into stock (ISSUER, NAME, PURCHASE_DATE, QUANTITY, SHARE_PRICE, STOCK_ID) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into bond (ISSUER, NAME, PURCHASE_DATE, INTEREST_RATE, MATURITY_DATE, VALUE, BOND_ID) values (?, ?, ?, ?, ?, ?, ?)
 */
