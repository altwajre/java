package com.company

class BillableBasket extends Basket {

    private CreditCardProcessor creditCardProcessor;

    public void checkout(){
        if(creditCardProcessor == null) {
            return
        }
        int total = 0
        for(Map.Entry<Product, Integer> entry : contents.entrySet()){
            total = total + (entry.getKey().getPrice() * entry.getValue());
        }
        creditCardProcessor.charge(total)
    }

    public void setCreditCardProcessor(CreditCardProcessor creditCardProcessor){
        this.creditCardProcessor = creditCardProcessor
    }
}
