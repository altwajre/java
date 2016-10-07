package com.company

import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

class TimeoutLimitSpec extends Specification {

    // 8.7 Declaring a test time-out
    // This test should finish within 2 seconds
    @Timeout(2)
    def "credit card charge happy path"() {
        given: "a basket, a customer and a TV"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name:"John",vip:false,creditCard:"testCard")

        and: "a credit card service"
        // The CreditCardProcessor class is an external service
        CreditCardProcessor creditCardSevice = new CreditCardProcessor()
        basket.setCreditCardProcessor(creditCardSevice)

        when: "user checks out the tv"
        basket.addProduct tv
        // This is a lengthy operation that contains the credit card service
        boolean success = basket.checkout(customer)

        sleep(2500)

        then: "credit card is charged"
        success
    }

    // 8.8 Delcaring a test time-out custom unit
    @Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
    def "credit card charge happy path - alt "() {
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

        sleep(2500)

        then: "credit card is charged"
        success
    }

}
