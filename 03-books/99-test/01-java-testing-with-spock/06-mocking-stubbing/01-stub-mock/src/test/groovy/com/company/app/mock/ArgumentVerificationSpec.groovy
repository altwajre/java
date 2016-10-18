package com.company.app.mock

import com.company.app.Product
import com.company.app.mocks.BillableBasket
import com.company.app.mocks.CreditCardProcessor
import com.company.app.mocks.Customer
import spock.lang.Specification

class ArgumentVerificationSpec extends Specification {

    // 6.22 Verifying exact arguments of a mocked method
    def "vip status is correctly passed to credit card - simple"(){

        given: "a basket, a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor) // Creating a Spock mock
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        basket.checkout(customer)

        then: "credit card is charged"

        // Expect the sale() to be called exactly once. Its first argument should be the number 1500,
        // and its second argument should be the customer instance.
        1 * creditCardProcessor.sale(1550, customer) // Verify the second argument is equal to a specific object instance

    }

    // 6.23 Verifying part of an object instance used as a mock argument
    def "vip status is correctly passed to credit card - vip"() {

        given: "a basket, a customer and a TV"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        Product camera = new Product(name:"panasonic",price:350,weight:2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name:"John",vip:false,creditCard:"testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardSevice = Mock(CreditCardProcessor) // Creating a Spock mock
        basket.setCreditCardProcessor(creditCardSevice)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        basket.checkout(customer)

        then: "credit card is charged"
        1 * creditCardSevice.sale(1550, { c -> c.vip == false}) // Verify the second has a field called vip with false

    }

    // 6.24 Using full Groovy closures for arguments verification
    def "vip status is correctly passed to credit card - full"(){

        given: "a basket, a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor) // Creation of a Spock mock
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        basket.checkout(customer)

        then: "credit card is charged"

        // Custom expression for both mocked arguments
        // Expect the sale method to be called exactly once.
        // The first argument should equal to basket.findOrderPrice()
        // The second argument Customer.vip should be false
        1 * creditCardProcessor.sale({amount -> amount == basket.findOrderPrice()}, {client -> client.vip == false})

    }

}
