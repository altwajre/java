package domain.model;

import domain.discount.CompositePricingStrategy;
import domain.discount.DiscountStore;
import domain.discount.ISalePricingStrategy;
import domain.discount.PricingStrategyFactory;
import domain.external.tax.ITaxCalculatorAdapter;
import ui.POSRuleEngineFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
  // Figure 26.4 Sale contains SalesLineItems and TaxLineItems
  private List<SalesLineItem> lineItems = new ArrayList<>();
  private List<TaxLineItem> taxLineItems = new ArrayList<>();
  private Date date = new Date();
  private boolean isComplete = false;
  private Payment payment;
  private Customer customer;
  private Money discount;
  private PricingStrategyFactory pricingStrategyFactory = PricingStrategyFactory.INSTANCE;
  private DiscountStore discountStore = DiscountStore.INSTANCE;
  private CompositePricingStrategy pricingStrategy = discountStore.getSalePricingStrategy();

  public void enterCustomerForDiscount(Customer customer) {
    this.customer = customer;
    pricingStrategyFactory.addCustomerPricingStrategy(this);
  }

  public Customer getCustomer() {
    return customer;
  }

  public CompositePricingStrategy getPricingStrategy() {
    pricingStrategy = discountStore.getSalePricingStrategy();
    return pricingStrategy;
  }

  private ITaxCalculatorAdapter taxCalculator;

  public void setTaxCalculator(ITaxCalculatorAdapter taxCalculator) {
    this.taxCalculator = taxCalculator;
  }

  public Money getBalance() {
    Money amount = payment.getAmount();
    amount.minus(getTotal());
    return amount;
  }

  public void becomeComplete() {
    isComplete = true;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void makeLineItem(ProductDescription product, int quantity) {
    SalesLineItem sli = new SalesLineItem(product, quantity);

    // do not add item if the rule engine (facade) detects it is invalid
    if(POSRuleEngineFacade.INSTANCE.isInvalid(sli, this)) {
      System.out.println("POSRuleEngineFacade.isInvalid");
      return;
    }

    lineItems.add(sli);
  }

  public Money getPreDiscountTotal() {
    Money total = new Money();
    Money subtotal = null;

    for(SalesLineItem lineItem : lineItems) {
      subtotal = lineItem.getSubtotal();
      total.add(subtotal);
    }

    return total;
  }

  public Money getTotal() {
    return pricingStrategy.getTotal(this);
  }

  public Money getDiscount() {
    ISalePricingStrategy salePricingStrategy = pricingStrategyFactory.getSalePricingStrategy();
    discount = salePricingStrategy.getTotal(this);

    return discount;
  }

  public void makePayment(Money amount) {
    payment = new Payment(amount);
  }

  public List<SalesLineItem> getSalesLineItems() {
    return lineItems;
  }

  public List<TaxLineItem> getTaxLineItems() {
    return taxLineItems;
  }

  public void addTaxLineItem(TaxLineItem taxLineItem) {
    taxLineItems.add(taxLineItem);
  }

  public List<TaxLineItem> getSalesTax() {
    return taxCalculator.getSalesTax(this);
  }

}
