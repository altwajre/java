package com.company.app;

import com.company.app.model.Currency;
import com.company.app.model.id.CurrencyId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209966.html

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

-----------------------------------------------------

3, run InfiniteFinancesSchema.sql first before running this app

4, database

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `currency`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `currency` (
  `NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `SYMBOL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`COUNTRY_NAME`)
)

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager1 = null;
    EntityManager entityManager2 = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager1 = entityManagerFactory.createEntityManager();
      entityManager1.getTransaction().begin();

      Currency currency = new Currency();
      currency.setCountryName("China");
      currency.setName("CNY");
      currency.setSymbol("¥");

      entityManager1.persist(currency);

      entityManager1.getTransaction().commit();

      entityManager2 = entityManagerFactory.createEntityManager();
      entityManager2.getTransaction().begin();

      Currency dbCurrency = entityManager2.find(Currency.class, new CurrencyId("CNY", "China"));
      System.out.println(dbCurrency.getName());

      entityManager2.getTransaction().commit();

    }
    catch (Exception e){
      entityManager1.getTransaction().rollback();
    }
    finally {
      entityManager1.close();
      entityManager2.close();
      entityManagerFactory.close();
    }
  }
}
/*
Hibernate: insert into currency (SYMBOL, COUNTRY_NAME, NAME) values (?, ?, ?)
Hibernate: select currency0_.COUNTRY_NAME as COUNTRY_1_0_0_, currency0_.NAME as NAME2_0_0_, currency0_.SYMBOL as SYMBOL3_0_0_ from currency currency0_ where currency0_.COUNTRY_NAME=? and currency0_.NAME=?
CNY
 */
