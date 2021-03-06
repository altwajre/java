package com.company.app;

import junit.framework.TestCase;

class InMemoryTranactionLog extends TransactionLog{
    public boolean wasSuccessLogged() {
        return true;
    }
}

class FakeCreditCardProcessor extends CreditCardProcessor{
    public double getAmountOfOnlyCharge() {
        return 100;
    }
}

public class RealBillingServiceTest extends TestCase {
    private final PizzaOrder order = new PizzaOrder(100);
    private final CreditCard creditCard = new CreditCard("1234", 11, 2010);
    private final InMemoryTranactionLog tranactionLog = new InMemoryTranactionLog();
    private final FakeCreditCardProcessor processor = new FakeCreditCardProcessor();
    //# setUp() and tearDown() are removed because we do not need any factory
    public void testSuccessfullCharge(){
        RealBillingService billingService = new RealBillingService(processor, tranactionLog);
        Receipt receipt = billingService.chargeOrder(order, creditCard);
        assertTrue(receipt.hasSuccessfulCharge());
        assertEquals(100.0, receipt.getAmountOfCharge());
        assertEquals(100.0, processor.getAmountOfOnlyCharge());
        assertTrue(tranactionLog.wasSuccessLogged());
    }
}