package com.company.app;

import lombok.ToString;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@ToString
class Address {
  public String lines;
  public String city;
  public String state;
  public String postal;
}
@ToString
class Person {
  public String given;
  public String family;
  public Address address;
}
@ToString
class Product {
  public String sku;
  public Integer quantity;
  public String description;
  public Float price;
}
@ToString
class Invoice {
  public Integer invoice;
  public String date;
  public Person billTo;
  public Person shipTo;
  public List<Product> product;
  public Float tax;
  public Float total;
  public String comments;
}
public class ConvertToPojoTest {

  /*
  yaml:
  --- !<tag:clarkevans.com,2002:invoice>
  invoice: 34843
  date   : 2001-01-23
  billTo: &id001
      given  : Chris
      family : Dumars
      address:
          lines: |
              458 Walkman Dr.
              Suite #292
          city    : Royal Oak
          state   : MI
          postal  : 48046
  shipTo: *id001
  product:
      - sku         : BL394D
        quantity    : 4
        description : Basketball
        price       : 450.00
      - sku         : BL4438H
        quantity    : 1
        description : Super Hoop
        price       : 2392.00
  tax  : 251.42
  total: 4443.52
  comments:
      Late afternoon is best.
      Backup contact is Nancy
      Billsmer @ 338-4338.
   */
  public static void loadInvoice() throws FileNotFoundException {

    String pathname = "src/main/resources/spec/01-invoice.yaml";
    InputStream inputStream = new FileInputStream(new File(pathname));
    Yaml yaml = new Yaml(new Constructor(Invoice.class));
    Invoice invoice = (Invoice) yaml.load(inputStream);
    System.out.println("#invoice");
    System.out.println(invoice);
    Person billTo = invoice.billTo;
    System.out.println("#person billTo");
    System.out.println(billTo);

  }
  /*
  output:
  #invoice
  Invoice(invoice=34843, date=2001-01-23, billTo=Person(given=Chris, family=Dumars, address=Address(lines=458 Walkman Dr.
  Suite #292
  , city=Royal Oak, state=MI, postal=48046)), shipTo=Person(given=Chris, family=Dumars, address=Address(lines=458 Walkman Dr.
  Suite #292
  , city=Royal Oak, state=MI, postal=48046)), product=[Product(sku=BL394D, quantity=4, description=Basketball, price=450.0), Product(sku=BL4438H, quantity=1, description=Super Hoop, price=2392.0)], tax=251.42, total=4443.52, comments=Late afternoon is best. Backup contact is Nancy Billsmer @ 338-4338.)
  #person billTo
  Person(given=Chris, family=Dumars, address=Address(lines=458 Walkman Dr.
  Suite #292
  , city=Royal Oak, state=MI, postal=48046))
   */
}
