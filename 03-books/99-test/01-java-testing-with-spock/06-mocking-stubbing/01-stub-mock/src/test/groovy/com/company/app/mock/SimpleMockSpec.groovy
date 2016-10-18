package com.company.app.mock

import com.company.app.Basket
import com.company.app.Product
import com.company.app.mocks.BillableBasket
import com.company.app.mocks.CreditCardProcessor
import com.company.app.mocks.Customer
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification

class SimpleMockSpec extends Specification {

    // 6.13 Stubbing mocks
    def "If the warehouse is empty nothing can be shipped"(){

        given: "a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "an empty warehouse"
        WarehouseInventory inventory = Mock(WarehouseInventory) // Create a mock
        inventory.isEmpty() >> true // Instruct the mock to return true when isEmpty() is called
        basket.setWarehouseInventory(inventory) // Inject the mock into the class under test

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely() // This method calls the mock behind the scenes

    }

    // 6.15 Verification of a mocked method
    def "credit card connection is always closed down"(){

        given: "a basket, a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor) // Creating a mock from an interface
        basket.setCreditCardProcessor(creditCardProcessor) // Injecting the mock into the class under test

        when: "user checks out the tv"
        basket.addProduct tv
        basket.checkout(customer) // This method calls the mock behind the scenes

        then: "connection is always closed at the end"

        // N * mockObject.method(arguments)
        // It is special Spock syntax; it makes test pass only if mock method has been called N times with arguments provided.
        1 * creditCardProcessor.shutdown() // Verifying called method

    }

}
