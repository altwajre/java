package com.company.app;

import java.util.ArrayList;
import java.util.List;

abstract class Stock{ // abstract 'Subject' = 'Publisher'
    private String symbol;
    private double price;
    private List<IInvestor> investors = new ArrayList<>();
    public Stock(String symbol, double price){
        this.symbol = symbol;
        this.price = price;
    }
    public void attach(IInvestor investor) {investors.add(investor);}
    public void detach(IInvestor investor) {investors.remove(investor);}
    private void notifyChange(){
        investors.forEach(i -> i.update(this));
    }
    public void setPrice(double price){
        if(this.price != price){
            this.price = price;
            notifyChange();
        }
    }
    public double getPrice(){
        return price;
    }
    public String getSymbol() {
        return symbol;
    }
}
class IBM extends Stock{ // concrete 'Subject' = 'Publisher'
    public IBM(String symbol, double price) {
        super(symbol, price);
    }
}
interface IInvestor{ // interface 'Observer' = 'Subscriber"
    void update(Stock stock);
}
class Investor implements IInvestor{ // concrete 'Observer' = 'Subscriber'
    private String name;
    private Stock stock;
    public Investor(String name){this.name = name;}
    @Override
    public void update(Stock stock) {
        System.out.printf("Notified %s of %s change to %s\n", name, stock.getSymbol(), stock.getPrice());
    }
}
public class App
{
    public static void main( String[] args )
    {
        IBM ibm = new IBM("IBM", 120.00);
        ibm.attach(new Investor("Tom"));
        ibm.attach(new Investor("Dick"));

        // notify investors when price changing
        ibm.setPrice(120.10);
        ibm.setPrice(130.00);
        ibm.setPrice(110.00);
    }
}
/*
output:
Notified Tom of IBM change to 120.1
Notified Dick of IBM change to 120.1
Notified Tom of IBM change to 130.0
Notified Dick of IBM change to 130.0
Notified Tom of IBM change to 110.0
Notified Dick of IBM change to 110.0
 */
