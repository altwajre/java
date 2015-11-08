package com.compnay.app;
public class App
{
    static class Receipt{
        public static Receipt forSuccessfulCharge(double amount) { return null; }
        public static Receipt forDeclinedCharge(String msg) { return null; }
        public static Receipt forSystemFailure(String message) { return null; }
    }
    static class PizzaOrder{
        public double getAmount(){ return 0; }
    }
    static class CreditCard{ }
    static class CreditCardProcessor{
        public ChargeResult charge(CreditCard creditCard, double amount){ return null; }
    }
    static class PaypalCreditCardProcessor extends CreditCardProcessor{}
    static class TransactionLog{
        public void logChargeResult(Object result) { }
        public void logConnectionException(Exception e) { }
    }
    static class DatabaseTransactionLog extends TransactionLog{}
    static class ChargeResult{
        public boolean wasSucessful() { return false; }
        public String getDeclineMessage() { return ""; }
    }
    public interface BillingService{
        Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
    }
    static public class RealBillingService implements BillingService{
        public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
            //# tight coupling code because directly new implementing classes below
            CreditCardProcessor processor = new PaypalCreditCardProcessor(); //#
            TransactionLog transactionLog = new DatabaseTransactionLog();    //#
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
