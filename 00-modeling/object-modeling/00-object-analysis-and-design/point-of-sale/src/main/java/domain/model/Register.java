package domain.model;

import domain.external.ServiceFactory;
import domain.external.accounting.IAccoutingAdapter;
import domain.external.accounting.SAPAccountingAdapter;
import domain.external.tax.ITaxCalculatorAdapter;

// POS system
public class Register {
  private ProductCatalog catalog;
  private Sale currentSale;
  private IAccoutingAdapter accountingAdapter;
  private ITaxCalculatorAdapter taxCalculatorAdapter;

  public Register(ProductCatalog catalog) {
    this.catalog = catalog;
  }

  // Singleton
  public void initialize() {
    accountingAdapter = ServiceFactory.INSTANCE.getAccountingAdapter();
    taxCalculatorAdapter = ServiceFactory.INSTANCE.getTaxCalculatorAdapter();
  }

  public void makeNewSale() {
    currentSale = new Sale();
  }

  public void enterItem(ItemID id, int quantity) {
    ProductDescription product = catalog.getProductDescription(id);
    currentSale.makeLineItem(product, quantity);
  }

  public void endSale() {
    currentSale.becomeComplete();
  }

  public void enterCustomerForDiscount(Integer customerId) {
    Store store = Store.INSTANCE;
    Customer customer = store.getCustomer(customerId);
    currentSale.enterCustomerForDiscount(customer);
  }

  public void makePayment(Money amount) {
    currentSale.makePayment(amount);
    IAccoutingAdapter accountingAdapter = new SAPAccountingAdapter();
    accountingAdapter.postSale(currentSale);
  }

  public Sale getCurrentSale() {
    return currentSale;
  }
}
