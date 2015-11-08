package com.company.app;

import junit.framework.TestCase;

class InMemoryTranactionLog extends App.TransactionLog{
    public boolean wasSuccessLogged() {
        return true;
    }
}
class FakeCreditCardProcessor extends App.CreditCardProcessor{
    public double getAmountOfOnlyCharge() {
        return 100;
    }
}
public class RealBillingServiceTest extends TestCase {
    private final App.PizzaOrder order = new App.PizzaOrder(100);
    private final App.CreditCard creditCard = new App.CreditCard("1234", 11, 2010);
    private final InMemoryTranactionLog tranactionLog = new InMemoryTranactionLog();
    private final FakeCreditCardProcessor processor = new FakeCreditCardProcessor();
    @Override public void setUp(){
        App.TransactionLogFactory.setInstance(tranactionLog);
        App.CreditCardProcessorFactory.setInstance(processor);
    }
    @Override public void tearDown(){
        App.TransactionLogFactory.setInstance(null);
        App.CreditCardProcessorFactory.setInstance(null);
    }
    public void testSuccessfullCharge(){
        App.RealBillingService billingService = new App.RealBillingService();
        App.Receipt receipt = billingService.chargeOrder(order, creditCard);
        assertTrue(receipt.hasSuccessfulCharge());
        assertEquals(100.0, receipt.getAmountOfCharge());
        assertEquals(100.0, processor.getAmountOfOnlyCharge());
        assertTrue(tranactionLog.wasSuccessLogged());
    }
}