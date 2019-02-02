package domain.discount;

import domain.model.*;
import org.junit.Test;

/*
Register

 */
public class CreateMultipleSalePricingStrategiesTest {
  @Test
  public void testRegisterBestForCustomerDiscount() {
    Register register = new Register(new ProductCatalog());
    register.makeNewSale();
    // ItemID was used as Map key, ItemID overrides equals and hashCode
    register.enterItem(new ItemID(100), 2);
    register.endSale();
    register.enterCustomerForDiscount(1);

    register.makePayment(new Money(100));

    Sale currentSale = register.getCurrentSale();
    System.out.println(currentSale.getTotal().getAmount());

    System.out.println("10% sale tax: " + String.format("%.2f", currentSale.getSalesTax()));
    System.out.println("10% sale discount: " + String.format("%.2f", currentSale.getDiscount().getAmount()));
  }
}
