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
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209970.html

> SQL tables

TODO: recreate following tables before running the app

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `stock`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `stock` (
  `INVESTMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ISSUER` varchar(45) NOT NULL,
  `PURCHASE_DATE` datetime NOT NULL,
  `SHARE_PRICE` decimal(10,2) NOT NULL,
  `QUANTITY` bigint(20) NOT NULL,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`),
  KEY `PORTFOLIO_FK_idx` (`PORTFOLIO_ID`),
  CONSTRAINT `PORTFOLIO_FK` FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `portfolio` (`PORTFOLIO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `bond`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `bond` (
  `INVESTMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ISSUER` varchar(100) NOT NULL,
  `PURCHASE_DATE` datetime NOT NULL,
  `VALUE` decimal(10,2) NOT NULL,
  `INTEREST_RATE` decimal(10,2) NOT NULL,
  `MATURITY_DATE` datetime NOT NULL,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`),
  KEY `PORTFOLIO_FK2_idx` (`PORTFOLIO_ID`),
  CONSTRAINT `PORTFOLIO_FK2` FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `portfolio` (`PORTFOLIO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

-----

describe ifinances_keys;
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| PK_NAME  | varchar(45) | NO   | PRI | NULL    |       |
| PK_VALUE | bigint(20)  | YES  | UNI | NULL    |       |
+----------+-------------+------+-----+---------+-------+

CREATE TABLE `ifinances_keys` (
  `PK_NAME` varchar(45) NOT NULL,
  `PK_VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PK_NAME`),
  UNIQUE KEY `PK_VALUE_UNIQUE` (`PK_VALUE`)
)

describe portfolio;
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| PORTFOLIO_ID | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| NAME         | varchar(100) | NO   |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+

CREATE TABLE `portfolio` (
  `PORTFOLIO_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`PORTFOLIO_ID`)
)

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

describe stock;
+---------------+---------------+------+-----+---------+----------------+
| Field         | Type          | Null | Key | Default | Extra          |
+---------------+---------------+------+-----+---------+----------------+
| INVESTMENT_ID      | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| NAME          | varchar(100)  | NO   |     | NULL    |                |
| ISSUER        | varchar(45)   | NO   |     | NULL    |                |
| PURCHASE_DATE | datetime      | NO   |     | NULL    |                |
| SHARE_PRICE   | decimal(10,2) | NO   |     | NULL    |                |
| QUANTITY      | bigint(20)    | NO   |     | NULL    |                |
| PORTFOLIO_ID  | bigint(20)    | YES  | MUL | NULL    |                |
+---------------+---------------+------+-----+---------+----------------+

CREATE TABLE `stock` (
  `INVESTMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ISSUER` varchar(45) NOT NULL,
  `PURCHASE_DATE` datetime NOT NULL,
  `SHARE_PRICE` decimal(10,2) NOT NULL,
  `QUANTITY` bigint(20) NOT NULL,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`),
  KEY `PORTFOLIO_FK_idx` (`PORTFOLIO_ID`),
  CONSTRAINT `PORTFOLIO_FK` FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `portfolio` (`PORTFOLIO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

describe bond;
+---------------+---------------+------+-----+---------+----------------+
| Field         | Type          | Null | Key | Default | Extra          |
+---------------+---------------+------+-----+---------+----------------+
| INVESTMENT_ID       | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| NAME          | varchar(100)  | NO   |     | NULL    |                |
| ISSUER        | varchar(100)  | NO   |     | NULL    |                |
| PURCHASE_DATE | datetime      | NO   |     | NULL    |                |
| VALUE         | decimal(10,2) | NO   |     | NULL    |                |
| INTEREST_RATE | decimal(10,2) | NO   |     | NULL    |                |
| MATURITY_DATE | datetime      | NO   |     | NULL    |                |
| PORTFOLIO_ID  | bigint(20)    | YES  | MUL | NULL    |                |
+---------------+---------------+------+-----+---------+----------------+

CREATE TABLE `bond` (
  `INVESTMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `ISSUER` varchar(100) NOT NULL,
  `PURCHASE_DATE` datetime NOT NULL,
  `VALUE` decimal(10,2) NOT NULL,
  `INTEREST_RATE` decimal(10,2) NOT NULL,
  `MATURITY_DATE` datetime NOT NULL,
  `PORTFOLIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`INVESTMENT_ID`),
  KEY `PORTFOLIO_FK2_idx` (`PORTFOLIO_ID`),
  CONSTRAINT `PORTFOLIO_FK2` FOREIGN KEY (`PORTFOLIO_ID`) REFERENCES `portfolio` (`PORTFOLIO_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

> Object model

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Investment {

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE,generator="key_generator")
  @TableGenerator(table="ifinances_keys",pkColumnName="pk_name",
      valueColumnName="pk_value", name="key_generator")
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
@Table(name = "stock")
public class Stock extends Investment {

  @Column(name = "SHARE_PRICE")
  private BigDecimal sharePrice;

  @Column(name = "QUANTITY")
  private BigDecimal quantity;
}

@Data
@Entity
@Table(name = "bond")
public class Bond extends Investment{

  @Column(name = "VALUE")
  private BigDecimal value;

  @Column(name = "INTEREST_RATE")
  private BigDecimal interestRate;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "MATURITY_DATE")
  private Date maturityDate;
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
      portfolio.setName("First Investments");

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
Hibernate: select tbl.pk_value from ifinances_keys tbl where tbl.pk_name=? for update
Hibernate: update ifinances_keys set pk_value=?  where pk_value=? and pk_name=?
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into portfolio (NAME, PORTFOLIO_ID) values (?, ?)
Hibernate: insert into stock (ISSUER, NAME, PORTFOLIO_ID, PURCHASE_DATE, QUANTITY, SHARE_PRICE, INVESTMENT_ID) values (?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into bond (ISSUER, NAME, PORTFOLIO_ID, PURCHASE_DATE, INTEREST_RATE, MATURITY_DATE, VALUE, INVESTMENT_ID) values (?, ?, ?, ?, ?, ?, ?, ?)
 */
