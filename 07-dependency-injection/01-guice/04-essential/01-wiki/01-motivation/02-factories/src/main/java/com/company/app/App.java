package com.company.app;

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
class CreditCardProcessorFactory{
    private static CreditCardProcessor instance;
    public static void setInstance(CreditCardProcessor processor){ instance = processor; }
    public static CreditCardProcessor getInstance(){ return instance == null ? new PaypalCreditCardProcessor() : instance; }
}
class TransactionLogFactory{
    private static TransactionLog instance;
    public static void setInstance(TransactionLog transactionLog){ instance = transactionLog; }
    public static TransactionLog getInstance(){ return instance == null ? new DatabaseTransactionLog() : instance; }
}
class RealBillingService implements BillingService{
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
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
