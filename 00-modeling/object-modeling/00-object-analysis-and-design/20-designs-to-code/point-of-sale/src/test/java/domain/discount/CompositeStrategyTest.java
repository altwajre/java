//package domain.discount;
//
//import domain.model.ItemID;
//import domain.model.Money;
//import domain.model.ProductDescription;
//import domain.model.Sale;
//import org.junit.Test;
//
//public class CompositeStrategyTest {
//  @Test
//  public void bestForCustomerDiscountTest() {
//    final PricingStrategyFactory pricingFactory = PricingStrategyFactory.INSTANCE;
//    ISalePricingStrategy discount = pricingFactory.getSalePricingStrategy(DiscountType.BestForCustomer);
//    ((CompositePricingStrategy) discount).add(new PercentDiscountPricingStrategy(0.90));
//    ((CompositePricingStrategy) discount).add(new AbsoluteOverThresholdPricingStrategy(new Money(1)));
//
//    Sale sale = new Sale();
//
//    Money price = new Money(2.5);
//    ItemID id = new ItemID(1);
//    ProductDescription product = new ProductDescription(id, price, "product 1");
//
//    sale.makeLineItem(product, 1);
//    sale.makeLineItem(product, 2);
//    sale.makeLineItem(product, 1);
//
//    System.out.println("Sale total: " + sale.getTotal().getAmount());
//
//    sale.setDiscountPricing(discount);
//    System.out.println("90% off Best For Customer Sale Discount: " + String.format("%.2f", sale.getDiscount().getAmount()));
//  }
//  /*
//Sale total: 10.0
//Best For Customer Sale Discount: 9.00
//   */
//}
