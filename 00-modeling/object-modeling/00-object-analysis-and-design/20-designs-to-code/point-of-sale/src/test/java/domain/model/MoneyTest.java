package domain.model;

import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTest {
  @Test
  public void testMoney() {
    Money money = new Money(new BigDecimal(1));
    money.add(new Money(new BigDecimal(3)));
    System.out.println("add - 1 + 3: "+money.getAmount());
    Money times = money.times(2);
    System.out.println("times 2: "+times.getAmount());
  }
}