package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/*
Definition
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified
and updated automatically.

https://github.com/jrogalsk/patterns/tree/master/src/main/java/com/jrsoft/learning/patterns/behavioural/observer/push_mode

More complex
Forces observer to update but provides information about data which changed
However pushed data might be inconsistent by the time update method is called
 */
abstract class Stock{  // abstract Subject
  private String symbol;
  private double price;
  private List<IInvestor> investors = new ArrayList<>();
  public Stock(String symbol, double price){
    this.symbol = symbol;
    this.price = price;
  }
  public void attach(IInvestor investor){ investors.add(investor); }
  public void detach(IInvestor investor){ investors.remove(investor); }
  public void notifyChange(){
    for(IInvestor investor : investors){ investor.update(this); }
    System.out.println("");
  }
  public String getSymbol() { return symbol; }
  public double getPrice() { return price; }
  public void setPrice(double price) {
    if(this.price != price){
      this.price = price;
      notifyChange();
    }
  }
}
class IBM extends Stock{  // Concrete Subject
  public IBM(String symbol, double price) { super(symbol, price); }
}
interface IInvestor{  // Observer interface
  void update(Stock stock);
}
class Investor implements IInvestor{  // Concrete Observer
  private String name;
  public Investor(String name){ this.name = name; }
  @Override
  public void update(Stock stock) {
    System.out.format("Notified %s of %s's change to %s\n", name, stock.getSymbol(), stock.getPrice());
  }
}

public class RealWorld {
  public static void main( String[] args )
  {
    IBM ibm = new IBM("IBM", 120.00);
    ibm.attach(new Investor("Tom"));
    ibm.attach(new Investor("Harry"));
    // Fluctuating prices will notify investors
    ibm.setPrice(120.10);
    ibm.setPrice(121.00);
    ibm.setPrice(120.50);
    ibm.setPrice(120.75);
  }
}
/*
Notified Tom of IBM's change to 120.1
Notified Harry of IBM's change to 120.1

Notified Tom of IBM's change to 121.0
Notified Harry of IBM's change to 121.0

Notified Tom of IBM's change to 120.5
Notified Harry of IBM's change to 120.5

Notified Tom of IBM's change to 120.75
Notified Harry of IBM's change to 120.75
 */
