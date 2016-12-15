package com.company;

import java.util.Map;

public class BillableBasket extends Basket {
    private CreditCardProcessor creditCardProcessor;

    public void setCreditCardProcessor(CreditCardProcessor creditCardProcessor) {
        this.creditCardProcessor = creditCardProcessor;
    }

    public boolean checkout(Customer customer)	{
        CreditCardResult result = creditCardProcessor.sale(findOrderPrice(), customer);
        creditCardProcessor.shutdown();

        return result == CreditCardResult.OK;
    }

    private int findOrderPrice() {
        int sum = 0;
        for (Map.Entry<Product, Integer> entry : contents.entrySet()) {
            int count = entry.getValue();
            int price = entry.getKey().getPrice();
            sum = sum + (count * price);
        }
        return sum;
    }
}
