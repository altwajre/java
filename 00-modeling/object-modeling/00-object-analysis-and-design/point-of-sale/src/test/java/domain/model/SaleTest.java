package domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleTest {
  @Test
  public void testSale() {
    // Step 1: Create the fixture

    // this is the object to test
    // it is an idiom to name sale as 'fixture'
    // it is often defined as an instance field rather than a local variable
    Sale sale = new Sale();

    // setup supporting objects for the test
    Money total = new Money(7.5);
    Money price = new Money(2.5);
    ItemID id = new ItemID(1);
    ProductDescription product = new ProductDescription(id, price, "product 1");

    // Step 2: Execute the method to test

    // Note: we write this code **imagining** there
    // is a makeLineItem method. This act of imagination
    // as we write the test tends to improve or clarify
    // our understanding of the detailed interface to
    // the object. Thus TDD has the side-benefit of
    // clarifying the detailed object design.

    // test makeLineItem
    sale.makeLineItem(product, 1);
    sale.makeLineItem(product, 2);

    // Step 3: Evaluate the results

    // there could be many assertTrue statements
    // for a complex evaluation

    // verify the total is 7.5
    assertEquals(total.getAmount(), sale.getTotal().getAmount());
    System.out.println("Sale total: " + sale.getTotal().getAmount());

  }
  /*
Sale total: 7.5
   */

}