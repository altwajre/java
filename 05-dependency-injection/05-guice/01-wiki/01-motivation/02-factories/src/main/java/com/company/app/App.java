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
    static public class CreditCardProcessorFactory{
        private static CreditCardProcessor instance;
        public static void setInstance(CreditCardProcessor processor){ instance = processor; }
        public static CreditCardProcessor getInstance(){ return instance == null ? new PaypalCreditCardProcessor() : instance; }
    }
    static public class TransactionLogFactory{
        private static TransactionLog instance;
        public static void setInstance(TransactionLog transactionLog){ instance = transactionLog; }
        public static TransactionLog getInstance(){ return instance == null ? new DatabaseTransactionLog() : instance; }
    }
    static public class RealBillingService implements BillingService{
        public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
            //# use factories to decouple the client and implementing classes below.
            //# run RealBillingServiceTest because the code is testable
            CreditCardProcessor processor = CreditCardProcessorFactory.getInstance(); //#
            TransactionLog transactionLog = TransactionLogFactory.getInstance();      //#
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
        System.out.println( "Hello World!" );
    }
}
