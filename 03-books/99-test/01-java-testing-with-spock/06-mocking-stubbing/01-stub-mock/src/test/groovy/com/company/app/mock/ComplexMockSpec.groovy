package com.company.app.mock

import com.company.app.Product
import com.company.app.mocks.BillableBasket
import com.company.app.mocks.CreditCardProcessor
import com.company.app.mocks.CreditCardResult
import com.company.app.mocks.Customer
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(BillableBasket.class)
class ComplexMockSpec extends Specification {

    // 6.25 Using mocks and stubs in the same test
    def "card has no funds"(){

        given: "a basket, a customer and some products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor) // Create a Spock mock
        basket.setCreditCardProcessor(creditCardProcessor)

        and: "a fully stocked warehouse"

        // Stub the inventory to be full
        WarehouseInventory inventory = Stub(WarehouseInventory){
            isProductAvailable( _, _) >> true
            isEmpty() >> false
        }
        basket.setWarehouseInventory(inventory)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        boolean charged = basket.fullCheckout(customer) // Trigger the tested action

        then: "nothing is charged if credit card does not have enough money"

        // Mock the credit card to be invalid
        // Directly mock the credit card processor to assume that the card doesn't have enough money, the charging process stops.
        1 * creditCardProcessor.authorize(1550, customer) >> CreditCardResult.NOT_ENOUGH_FUNDS

        // Verify that nothing was charged
        !charged

        0 * _

    }

    // 6.26 Verifying a sequence of events with interconnected method calls
    def "happy path for credit card sale"(){

        given: "a basket, a customer and some products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card that has enough funds"
        CreditCardProcessor creditCardProcessor = Mock(CreditCardProcessor) // Mock the credit card service
        basket.setCreditCardProcessor(creditCardProcessor)
        CreditCardResult sampleResult = CreditCardResult.OK
        sampleResult.setToken("sample") // Create a sample credit card token

        and: "a warehouse"
        WarehouseInventory inventory = Mock(WarehouseInventory) // Mock the warehouse
        basket.setWarehouseInventory(inventory)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        boolean charged = basket.fullCheckout(customer) // Trigger the tested action

        then: "credit card is checked"
        // Pass the sample token to the basket class
        1 * creditCardProcessor.authorize(1550, customer) >> sampleResult

        then: "inventory is checked"
        // Group interactions using with()
        with(inventory){
            // Verify that the inventory is queried twice (once for each product)
            2 * isProductAvailable(!null, 1) >> true
            _ * isEmpty() >> false
        }

        then: "credit card is charged"

        // Verify the previous token is reused by the basket class
        1 * creditCardProcessor.capture({myToken -> myToken.endsWith("sample")}, customer) >> CreditCardResult.OK

        // Verify the credit card was charged
        charged

        // Ensure that no other method from mocks was called
        0 * _

    }

}
