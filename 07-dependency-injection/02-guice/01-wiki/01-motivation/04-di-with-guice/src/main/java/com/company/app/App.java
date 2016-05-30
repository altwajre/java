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
class TransactionLog{
    public void logChargeResult(Object result) { }
    public void logConnectionException(Exception e) { }
}
class DatabaseTransactionLog extends TransactionLog{}
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
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
        bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
        bind(BillingService.class).to(RealBillingService.class);
    }
}
class RealBillingService implements BillingService{
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
public class App
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        System.out.println(billingService.getClass().getName());
    }
}
