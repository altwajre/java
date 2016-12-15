package com.company.ignore

import com.company.BillableBasket
import com.company.CreditCardProcessor
import com.company.Customer
import com.company.Product
import spock.lang.Ignore
import spock.lang.Specification

class IgnoreSpec extends Specification {

    // 8.9 Ignoring a single test
    // This test will be skipped when Spock runs it
    @Ignore("Until credit card server is migrated")
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
