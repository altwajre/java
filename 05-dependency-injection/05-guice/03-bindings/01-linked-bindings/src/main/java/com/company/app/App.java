package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// https://github.com/google/guice/wiki/GettingStarted
public class App
{
    static class Receipt{
        public static Receipt forSuccessfulCharge(double amount) { return new Receipt(); }
        public static Receipt forDeclinedCharge(String msg) { return new Receipt(); }
        public static Receipt forSystemFailure(String message) { return new Receipt(); }
        public boolean hasSuccessfulCharge() { return true; }
        public double getAmountOfCharge() { return 100; }
    }
    static class PizzaOrder{
        private double amount;
        public PizzaOrder(double amount){this.amount = amount;}
        public double getAmount(){ return amount; }
    }
    static class CreditCard{ public CreditCard(String cardNumber, Integer month, Integer year){ } }
    static class CreditCardProcessor{
        public ChargeResult charge(CreditCard creditCard, double amount){ return new ChargeResult(); }
    }
    static class PaypalCreditCardProcessor extends CreditCardProcessor{}
    interface TransactionLog{
        void logChargeResult(Object result);
        void logConnectionException(Exception e);
    }
    static class DatabaseTransactionLog implements TransactionLog{
        public void logChargeResult(Object result) { }
        public void logConnectionException(Exception e) { }
    }
    static class MySqlDatabaseDatabaseTransactionLog extends DatabaseTransactionLog{}
    static class ChargeResult{
        public boolean wasSucessful() { return true; }
        public String getDeclineMessage() { return ""; }
    }
    interface BillingService{
        Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
    }
    //# map interfaces to implementations
    public static class BillingModule extends AbstractModule{
        @Override
        protected void configure() {
            // map interface TransactionLog to implementation DatabaseTransactionLog
            bind(TransactionLog.class).to(DatabaseTransactionLog.class);
            // link DatabaseTransactionLog class to a subclass MySqlDatabaseDatabaseTransactionLog
            bind(DatabaseTransactionLog.class).to(MySqlDatabaseDatabaseTransactionLog.class);
        }
    }
    // output: com.company.app.App$MySqlDatabaseDatabaseTransactionLog
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new BillingModule());
        TransactionLog transactionLog = injector.getInstance(TransactionLog.class);
        System.out.println(transactionLog.getClass().getName());
    }
}
