import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Value Objects

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video4_2
 */
class Money { // Value Object
  private double money;

  public Money(double d) {
    this.money = BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
  }

  public double getMoney() {
    return money;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Money) {
      double extracted1 = BigDecimal.valueOf(((Money) obj).getMoney()).setScale(0, RoundingMode.HALF_EVEN).doubleValue();
      double extracted2 = BigDecimal.valueOf(this.getMoney()).setScale(0, RoundingMode.HALF_EVEN).doubleValue();
      if (extracted1 == extracted2) {
        return true;
      }
    }
    return false;
  }
}

class Order { // Value Object
  private Money money;

  public Order(Money money) {
    this.money = money;
  }

  public Money getMoney() {
    return this.money;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Order) {
      Order orderToCompare = (Order) obj;
      if (this.money.equals(orderToCompare.getMoney())) {
        return true;
      }
    }
    return false;
  }
}

public class ValueObjects {
  public static void main(String[] args) {
    System.out.println("# Money Value Object");
    Money p1 = new Money(100.568965);
    Money p2 = new Money(100.778899);

    System.out.println("p1.getMoney(): " + p1.getMoney());
    System.out.println("p2.getMoney(): " + p2.getMoney());

    System.out.println("p1.equals(p2): " + p1.equals(p2));

    System.out.println("# Order Value Object contains Money Value Object");
    Order o1 = new Order(p1);
    Order o2 = new Order(p2);
    System.out.println("o1.equals(o2): " + o1.equals(o2));
  }
}
/*
# Money Value Object
p1.getMoney(): 100.57
p2.getMoney(): 100.78
p1.equals(p2): true
# Order Value Object contains Money Value Object
o1.equals(o2): true
 */
