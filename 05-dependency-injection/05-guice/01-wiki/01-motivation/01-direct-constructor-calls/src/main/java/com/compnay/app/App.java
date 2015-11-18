package com.compnay.app;

class Receipt{
    public static Receipt forSuccessfulCharge(double amount) { return null; }
    public static Receipt forDeclinedCharge(String msg) { return null; }
    public static Receipt forSystemFailure(String message) { return null; }
}
class PizzaOrder{
    public double getAmount(){ return 0; }
}
class CreditCard{ }
class CreditCardProcessor{
    public ChargeResult charge(CreditCard creditCard, double amount){ return null; }
}
class PaypalCreditCardProcessor extends CreditCardProcessor{}
class TransactionLog{
    public void logChargeResult(Object result) { }
    public void logConnectionException(Exception e) { }
}
class DatabaseTransactionLog extends TransactionLog{}
class ChargeResult{
    public boolean wasSucessful() { return false; }
    public String getDeclineMessage() { return ""; }
}
interface BillingService{
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
class RealBillingService implements BillingService{
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
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
