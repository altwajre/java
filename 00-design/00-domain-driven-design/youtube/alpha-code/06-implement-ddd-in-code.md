# Implementing DDD in code

https://www.youtube.com/watch?v=B-9RsRmC6sA&list=PLZBNtT95PIW3BPNYF5pYOi4MJjg_boXCG&index=7

## Value Objects aka Domain Primitives

> Bad

public class User {
  String name;
  String email;
  String mobile;
  ...
}

public class Product {
  int rating;
  int quantity;
  String title;
  String description;
  BigDecimal price;
  ...
}

> Good

public class User {
  Name name;
  Email email;
  PhoneNumber mobile; // PhoneNumber has max number of digit allowed
  ...
}

public class Product {
  Rating rating;
  Quantity quantity;
  Title title;
  Description description;
  Money price;
  ...
}

### Code example

https://www.youtube.com/watch?v=aZAEjVFpU1s

- Implicit Concerns - Everything in the code should be EXPLICIT.

> Bad

- Magic Numbers - 100, 4.99
- Duplicate code - BigDecimal
- Primitive Obsession
- Mixed Concerns - SQLException
- Fuzzy Terminology - Item i: i is fuzzy terminology

public class ShippingController extends Controller {
  private CartService cartService;
  public void process(ShippingForm form) throws Exception, SQLException {
    CartBean cartBean = cartService.getCart(form.getCartId());
    BigDecimal cost = null;

    if(cartBean.getTotal() <= 100) {
      cost = new BigDecimal(4.99);
      if(form.getOption() == 1) {
        for(Item i : cartBean.getItems()) {
          if(i.getCat() == 'B') {
            cost.add(new BigDecimal(2.99));
          }
          if(i.getCat() == 'O') {
            cost.add(new BigDecimal(i.getWeight() / 1000).multiply(new BigDecimal(2.99)));
          }
        }
      }
      if(form.getOption() == 2) {
        for(Item i : cartBean.getItems()) {
          if(i.getCat() == 'B') {
            cost.add(new BigDecimal(4.99));
          }
          if(i.getCat() == 'O') {
            cost.add(new BigDecimal(i.getWeight() / 1000).multiply(new BigDecimal(2.99)));
          }
        }
      }
    }
    form.setCost(cost.setScale(0, ROUND_HALF_EVEN));
  }
}

> Good - Domain Driven Code

public Money calculateShippingCost(Cart cart, ShippingOption selectedOption) {
  if(cart.getTotalPrice().lessThan(THRESHOLD_FOR_FREE_SHIPPING)) {
    Money shippingCost = getBaseShippmentCost(cart);
    for(Item item : cart.getItems()) {
      if(selectedOption == REGULAR) {
        shippingCost = shippingCost.add(RegularShipping.getCostForItem(item));
      }
      else if(selectedOption == PRIME) {
        shippingCost = shippingCost.add(PrimeShipping.getCostForItem(item));
      }
    }
    return shippingCost;
  }
  return Money.ZERO;
}
