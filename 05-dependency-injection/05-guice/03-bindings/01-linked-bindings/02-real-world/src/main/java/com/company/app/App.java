package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

class Receipt{
    public static Receipt forSuccessfulCharge(double amount) { return new Receipt(); }
    public static Receipt forDeclinedCharge(String msg) { return new Receipt(); }
    public static Receipt forSystemFailure(String message) { return new Receipt(); }
    public boolean hasSuccessfulCharge() { return true; }
    public double getAmountOfCharge() { return 100; }
}
class PizzaOrder{
    private double amount;
    public PizzaOrder(double amount){this.amount = amount;}
    public double getAmount(){ return amount; }
}
class CreditCard{ public CreditCard(String cardNumber, Integer month, Integer year){ } }
class CreditCardProcessor{
    public ChargeResult charge(CreditCard creditCard, double amount){ return new ChargeResult(); }
}
class PaypalCreditCardProcessor extends CreditCardProcessor{}
interface TransactionLog{
    void logChargeResult(Object result);
    void logConnectionException(Exception e);
}
class DatabaseTransactionLog implements TransactionLog{
    public void logChargeResult(Object result) { }
    public void logConnectionException(Exception e) { }
}
class MySqlDatabaseDatabaseTransactionLog extends DatabaseTransactionLog{}
class ChargeResult{
    public boolean wasSucessful() { return true; }
    public String getDeclineMessage() { return ""; }
}
interface BillingService{
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
//# map interfaces to implementations
class BillingModule extends AbstractModule{
    @Override
    protected void configure() {
        // map interface TransactionLog to implementation DatabaseTransactionLog
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
        // link DatabaseTransactionLog class to a subclass MySqlDatabaseDatabaseTransactionLog
        bind(DatabaseTransactionLog.class).to(MySqlDatabaseDatabaseTransactionLog.class);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new BillingModule());
        TransactionLog transactionLog = injector.getInstance(TransactionLog.class);
        System.out.println(transactionLog.getClass().getName());
    }
}
/*
https://github.com/google/guice/wiki/LinkedBindings

output:
com.company.app.App$MySqlDatabaseDatabaseTransactionLog
 */
