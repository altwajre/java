package com.company.app.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class Tax {
    public double calcTax(double income, double rate){
        return income * (rate / 100);
    }
}

public class TaxTest {
    @Test
    public void calc_tax_test(){
        Tax tax = new Tax();
        double actual = tax.calcTax(100, 8);
        double expected = 8;
        assertEquals(expected, actual, 0);
    }
}

