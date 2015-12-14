package com.company.app;
import java.util.ArrayList;
import java.util.List;

abstract class Stock{  // abstract Subject
    private String symbol;
    private double price;

    private List<IInvestor> investors = new ArrayList<IInvestor>();
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
    private Stock stock;
    public Investor(String name){ this.name = name; }
    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }
    public void update(Stock stock) {
        System.out.format("Notified %s of %s's change to %s\n", name, stock.getSymbol(), stock.getPrice());
    }
}
public class App
{
    public static void main( String[] args )
    {
        IBM ibm = new IBM("IBM", 120.00);
        ibm.attach(new Investor("Sorros"));
        ibm.attach(new Investor("Berkshire"));
        // Fluctuating prices will notify investors
        ibm.setPrice(120.10);
        ibm.setPrice(121.00);
        ibm.setPrice(120.50);
        ibm.setPrice(120.75);
    }
}
/*
Definition
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified
and updated automatically.

output:
Notified Sorros of IBM's change to 120.1
Notified Berkshire of IBM's change to 120.1

Notified Sorros of IBM's change to 121.0
Notified Berkshire of IBM's change to 121.0

Notified Sorros of IBM's change to 120.5
Notified Berkshire of IBM's change to 120.5

Notified Sorros of IBM's change to 120.75
Notified Berkshire of IBM's change to 120.75
 */
