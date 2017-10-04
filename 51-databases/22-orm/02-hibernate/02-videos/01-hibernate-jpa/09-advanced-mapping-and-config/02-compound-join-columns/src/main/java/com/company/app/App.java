package com.company.app;

import com.company.app.model.Currency;
import com.company.app.model.Market;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209967.html

> SQL tables

describe currency;
+--------------+-------------+------+-----+---------+-------+
| Field        | Type        | Null | Key | Default | Extra |
+--------------+-------------+------+-----+---------+-------+
| NAME         | varchar(45) | NO   | PRI | NULL    |       |
| COUNTRY_NAME | varchar(45) | NO   | PRI | NULL    |       |
| SYMBOL       | varchar(45) | YES  |     | NULL    |       |
+--------------+-------------+------+-----+---------+-------+

CREATE TABLE `currency` (
  `NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `SYMBOL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`COUNTRY_NAME`)
)

describe market;
+---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| MARKET_ID     | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| CURRENCY_NAME | varchar(45) | NO   | MUL | NULL    |                |
| COUNTRY_NAME  | varchar(45) | NO   |     | NULL    |                |
| MARKET_NAME   | varchar(45) | YES  |     | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+

CREATE TABLE `market` (
  `MARKET_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CURRENCY_NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `MARKET_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MARKET_ID`),
  KEY `CURRENCY_FK_idx` (`CURRENCY_NAME`,`COUNTRY_NAME`),
  CONSTRAINT `CURRENCY_FK` FOREIGN KEY (`CURRENCY_NAME`, `COUNTRY_NAME`) REFERENCES `currency` (`NAME`, `COUNTRY_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

> Object model

@Entity
@IdClass(CurrencyId.class)
public class Currency {

  @Id
  @Column(name="NAME")
  private String name;

  @Id
  @Column(name="COUNTRY_NAME")
  private String countryName;

- create a class to hold the ids

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class CurrencyId implements Serializable {

  private String name;

  private String countryName;
}

@Data
@Entity
@Table(name = "MARKET")
public class Market {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MARKET_ID")
  private Long marketId;

  @ManyToOne(cascade= CascadeType.ALL)
  @JoinColumns({
      @JoinColumn(name="CURRENCY_NAME", referencedColumnName="NAME"),
      @JoinColumn(name="COUNTRY_NAME", referencedColumnName="COUNTRY_NAME")
  })
  private Currency currency;

-----------------------------------------------------

1, run InfiniteFinancesSchema.sql first before running this app

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Currency currency = new Currency();
      currency.setCountryName("China");
      currency.setName("CNY");
      currency.setSymbol("¥");

      Market market = new Market();
      market.setMarketName("London Stock Exchange");
      market.setCurrency(currency);

      entityManager.persist(market);
      entityManager.getTransaction().commit();

      Market dbMarket = entityManager.find(Market.class, market.getMarketId());
      System.out.println(dbMarket.getCurrency().getName());

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
Hibernate: insert into currency (SYMBOL, COUNTRY_NAME, NAME) values (?, ?, ?)
Hibernate: insert into market (COUNTRY_NAME, CURRENCY_NAME, MARKET_NAME, MARKET_ID) values (?, ?, ?, ?)
CNY
 */
