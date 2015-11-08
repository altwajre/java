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
    static class TransactionLog{
        public void logChargeResult(Object result) { }
        public void logConnectionException(Exception e) { }
    }
    static class DatabaseTransactionLog extends TransactionLog{}
    static class ChargeResult{
        public boolean wasSucessful() { return true; }
        public String getDeclineMessage() { return ""; }
    }
    public interface BillingService{
        Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
    }
    //# map interfaces to implementations
    public static class BillingModule extends AbstractModule{
        @Override
        protected void configure() {
            bind(TransactionLog.class).to(DatabaseTransactionLog.class);
            bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
            bind(BillingService.class).to(RealBillingService.class);
        }
    }
    static public class RealBillingService implements BillingService{
        private final CreditCardProcessor processor;
        private final TransactionLog transactionLog;
        //# add @Inject to RealBillingService's constructor
        //# Guice will inspect the annotated constructor, and lookup values for each parameter.
        @Inject
        public RealBillingService(CreditCardProcessor processor, TransactionLog transactionLog){
            this.processor = processor;
            this.transactionLog = transactionLog;
        }
        public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
            try{
                ChargeResult result = processor.charge(creditCard, order.getAmount());
                transactionLog.logChargeResult(result);
                return result.wasSucessful()
                        ? Receipt.forSuccessfulCharge(order.getAmount())
                        : Receipt.forDeclinedCharge(result.getDeclineMessage());
            }catch (Exception e){
                transactionLog.logConnectionException(e);
                return Receipt.forSystemFailure(e.getMessage());
            }
        }
    }
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        System.out.println(billingService.getClass().getName());
    }
}
