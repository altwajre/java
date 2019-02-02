package domain.model;

import org.junit.Test;

public class RegisterTest {
  @Test
  public void testRegister() {
    Register register = new Register(new ProductCatalog());
    register.makeNewSale();
    // ItemID was used as Map key, ItemID overrides equals and hashCode
    register.enterItem(new ItemID(100), 2);
    register.endSale();
    Sale currentSale = register.getCurrentSale();

    // senior discount 20%
    register.enterCustomerForDiscount(1);
    System.out.println("Sale Discount: "+currentSale.getDiscount());
    System.out.println("Sale PreDiscountTotal: "+currentSale.getPreDiscountTotal());
    System.out.println("Sale Total: " + currentSale.getTotal());

    register.makePayment(new Money(100));
    System.out.println("Sale Balance: "+currentSale.getBalance());

  }
}
/*
20
10% sale tax: 2.00
10% sale discount: 2.00
 */
