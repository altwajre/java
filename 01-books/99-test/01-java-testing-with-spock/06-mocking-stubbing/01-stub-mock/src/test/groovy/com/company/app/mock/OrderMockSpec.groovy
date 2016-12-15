package com.company.app.mock

import com.company.app.Product
import com.company.app.mocks.BillableBasket
import com.company.app.mocks.CreditCardProcessor
import com.company.app.mocks.Customer
import spock.lang.Specification
import spock.lang.Subject

@Subject(BillableBasket.class)
class OrderMockSpec extends Specification {

    def "credit card connection is closed down in the end - incorrect"(){

        given: "a basket, a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out the tv"
        basket.addProduct tv
        basket.checkout(customer)

        // Order of invocations in then block does not matter to Spock
        then: "credit card is charged and CC service is closed down"
        1 * creditCardProcessor.shutdown()
        1 * creditCardProcessor.sale(1200, customer)

    }

    def "credit card connection is closed down in the end"(){

        given: "a basket, a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor) // Creation of a mock from an interface
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out the tv"
        basket.addProduct tv
        basket.checkout(customer)

        // First this verification will be checked
        then: "credit card is charged and"
        1 * creditCardProcessor.sale( _, _)

        // This will only be checked if the first verification passes
        then: "the credit card service is closed down"
        1 * creditCardProcessor.shutdown()

    }
}
