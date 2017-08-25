package com.company.ignore

import com.company.BillableBasket
import com.company.CreditCardProcessor
import com.company.Customer
import com.company.Product
import spock.lang.IgnoreIf
import spock.lang.Specification

class IgnoreDynamicPrecondition extends Specification {

    // 8.12 Skipping a Spock test based on a dynamic precondition
    // This test will run only if the method online() of the credit card service return true
    @IgnoreIf({ !new CreditCardProcessor().online() })
    def "credit card charge happy path - alt"() {

        given: "a basket, a customer and a TV"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name:"John",vip:false,creditCard:"testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardSevice = new CreditCardProcessor()
        basket.setCreditCardProcessor(creditCardSevice)

        when: "user checks out the tv"
        basket.addProduct tv
        boolean success = basket.checkout(customer)

        then: "credit card is charged"
        success

    }


    // 8.13 Requires is the opposite of IgnoreIf
    // This test will run only if the method online() of the credit card service returns true
    @IgnoreIf({new CreditCardProcessor().online()})
    def "credit card charge happy path"() {

        given: "a basket, a customer and a TV"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name:"John",vip:false,creditCard:"testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardSevice = new CreditCardProcessor()
        basket.setCreditCardProcessor(creditCardSevice)

        when: "user checks out the tv"
        basket.addProduct tv
        boolean success = basket.checkout(customer)

        then: "credit card is charged"
        success

    }

}
