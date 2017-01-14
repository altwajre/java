package com.company

import spock.lang.AutoCleanup
import spock.lang.Specification

class AutoCleanupSpec extends Specification {

    // 8.14 Releasing resources with AutoCleanup
    // The shutdown() method of the credit card service will be called at the end of the tests
    @AutoCleanup("shutdown")
    private CreditCardProcessor creditCardSevice = new CreditCardProcessor()

    def "credit card connection is closed down in the end"() {

        given: "a basket, a customer and a TV"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name:"John",vip:false,creditCard:"testCard")

        and: "a credit card service"
        basket.setCreditCardProcessor(creditCardSevice)

        when: "user checks out the tv"
        basket.addProduct tv
        boolean success = basket.checkout(customer)

        then: "credit card is charged"
        success

    }
}
