package domain.external.tax;

import domain.external.ServiceFactory;
import domain.model.*;
import org.junit.Test;

public class GoldTaxTest {
  @Test
  public void testSalesTax() {
    final ServiceFactory serviceFactory = ServiceFactory.INSTANCE;
    ITaxCalculatorAdapter taxCalculator = serviceFactory.getTaxCalculatorAdapter();

    Sale sale = new Sale();

    Money price = new Money(5);
    ItemID id = new ItemID(1);
    ProductDescription product = new ProductDescription(id, price, "product 1");

    sale.makeLineItem(product, 2);
    sale.makeLineItem(product, 2);

    System.out.println("Sale total: " + sale.getTotal().getAmount());

    sale.setTaxCalculator(taxCalculator);
    sale.addTaxLineItem(new TaxLineItem("WA State Tax", .1));

    System.out.println("10% sale tax: " + String.format("%.2f", taxCalculator.getSalesTax(sale)));

  }
}
