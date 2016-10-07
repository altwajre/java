package com.company.ignore

import com.company.BillableBasket
import com.company.CreditCardProcessor
import com.company.Customer
import com.company.Product
import spock.lang.IgnoreRest
import spock.lang.Specification

// 8.10 Ignoring all tests except one
class IgnoreRestSpec extends Specification {

    // This test uses the real credit card service - it will be skipped
    def "credit card charge - integration test"() {
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

    // Marking this test as the only one that will run
    @IgnoreRest
    def "credit card charge with mock"() {
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

    // This test uses the real credit card service - it will be skipped
    def "credit card charge no charge - integration test"() {
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
