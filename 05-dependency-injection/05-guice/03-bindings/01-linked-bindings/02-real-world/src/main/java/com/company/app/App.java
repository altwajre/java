package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

class Receipt{
}
class Order{
}
class PizzaOrder extends Order{
}
interface TransactionLog{
    void log();
}
class DatabaseTransactionLog implements TransactionLog{
    public void log() {
        System.out.println(this.getClass().getName());
    }
}
interface BillingService{
    Receipt chargeOrder(PizzaOrder order);
    void print();
}
//# map interfaces to implementations
class BillingModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
        bind(BillingService.class).to(RealBillingService.class);
        bind(Order.class).to(PizzaOrder.class);
    }
}
class RealBillingService implements BillingService{
    private final TransactionLog transactionLog;
    private PizzaOrder order;
    //# add @Inject to RealBillingService's constructor
    //# Guice will inspect the annotated constructor, and lookup values for each parameter.
    @Inject // constructor inject
    public RealBillingService(TransactionLog transactionLog){
        this.transactionLog = transactionLog;
    }
    @Inject // method inject
    public Receipt chargeOrder(PizzaOrder order) {
        this.order = order;
        return null;
    }
    public void print(){
        this.transactionLog.log();
        System.out.println(order.getClass().getSimpleName());
    }
}
public class App
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        System.out.println(billingService.getClass().getName());
        billingService.print();
    }
}
/*
https://github.com/google/guice/wiki/LinkedBindings

output:
com.company.app.RealBillingService
com.company.app.DatabaseTransactionLog
PizzaOrder
 */
