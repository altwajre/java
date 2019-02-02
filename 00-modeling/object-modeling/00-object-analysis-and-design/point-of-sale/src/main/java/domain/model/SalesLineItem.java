package domain.model;

public class SalesLineItem {
  private int quantity;
  private ProductDescription product;

  public SalesLineItem(ProductDescription product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Money getSubtotal() {
    return product.getPrice().times(quantity);
  }

  public ProductDescription getProduct() {
    return product;
  }
}
