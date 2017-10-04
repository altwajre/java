package com.company.app;

import com.company.app.model.Currency;

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
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Currency currency = new Currency();
      currency.setCountryName("China");
      currency.setName("CNY");
      currency.setSymbol("¥");

      entityManager.persist(currency);

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
Hibernate: insert into currency (SYMBOL, COUNTRY_NAME, NAME) values (?, ?, ?)
 */
