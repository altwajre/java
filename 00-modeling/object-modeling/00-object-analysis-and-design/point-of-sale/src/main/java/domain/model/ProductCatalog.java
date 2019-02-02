package domain.model;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
  private Map<ItemID, ProductDescription> descriptions = new HashMap<>();
  public ProductCatalog() {
    // sample date
    ItemID id1 = new ItemID(100);
    ItemID id2 = new ItemID(200);
    Money price = new Money(10);

    ProductDescription product1 = new ProductDescription(id1, price, "product 1");
    descriptions.put(product1.getItemID(), product1);
    ProductDescription product2 = new ProductDescription(id2, price, "product 2");
    descriptions.put(product2.getItemID(), product2);
  }
  public ProductDescription getProductDescription(ItemID id) {
    return descriptions.get(id);
  }
}
