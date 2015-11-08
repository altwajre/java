package com.company.app;

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
    static public class RealBillingService implements BillingService{
        private final CreditCardProcessor processor;
        private final TransactionLog transactionLog;
        //# use dependency injection to pass classes in as constructor parameters.
        //# do not need any factory
        //# run RealBillingServiceTest because the code is testable
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
        //# lookup its dependencies. need to construct dependencies recursively to use a service.
        CreditCardProcessor processor = new PaypalCreditCardProcessor();
        TransactionLog transactionLog = new DatabaseTransactionLog();
        BillingService billingService = new RealBillingService(processor, transactionLog);
        System.out.println(billingService.getClass().getName());
    }
}
