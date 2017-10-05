package com.company.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
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

 */

@Data
@Entity
@Table(name = "market")
public class Market {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MARKET_ID")
  private Long marketId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumns({
      @JoinColumn(name = "CURRENCY_NAME", referencedColumnName = "NAME"),
      @JoinColumn(name = "COUNTRY_NAME", referencedColumnName = "COUNTRY_NAME")
  })
  private Currency currency;

  @Column(name = "MARKET_NAME")
  private String marketName;

}
