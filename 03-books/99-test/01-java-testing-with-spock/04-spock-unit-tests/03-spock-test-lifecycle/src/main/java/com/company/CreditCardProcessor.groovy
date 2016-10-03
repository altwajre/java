package com.company

class CreditCardProcessor {

    private int salesToday

    public CreditCardProcessor(){
        salesToday = 0
    }

    void newDayStarted(){
        salesToday = 0
    }

    void charge(int price){
        salesToday += price
    }

    int getCurrentRevenue(){
        return salesToday
    }

    void shutDown(){

    }

}
