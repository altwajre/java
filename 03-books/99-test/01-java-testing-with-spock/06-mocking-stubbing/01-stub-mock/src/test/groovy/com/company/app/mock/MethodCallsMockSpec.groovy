package com.company.app.mock

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.ShippingCalculator
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket.class)
class MethodCallsMockSpec extends Specification {

    // 6.17 Explicit declaration of interactions
    def "Warehouse is queried for each product"(){

        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory) // Creating a mock/stub
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely() // Mocks are only checks in the when: block

        then: "order can be shipped"
        readyToShip // When using mocks, the then: block must contain only verifications

        // After test is finished, expect the method isProductAvailable() was called twice;
        // Don't care about the arguments.
        // But when it's called, please returnn true to the class that called it.
        2 * inventory.isProductAvailable( _, _) >> true // Stubbing a mocked method
        0 * inventory.preload( _, _) // Verifying that a method was never called

    }

    // 6.18 Verifying interactions for all methods of a class
    def "Warehouse is queried for each product - strict"(){

        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely() // Mocks are only checks in the when: block

        then: "order can be shipped"
        readyToShip
        // Setting expectation for specific methods
        2 * inventory.isProductAvailable( _, _) >> true
        1 * inventory.isEmpty() >> false

        // Setting expectations for all other methods of the class
        0 * inventory._

    }

    def "Only warehouse is queried when checking shipping status"(){

        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)
        ShippingCalculator shippingCalculator = Mock(ShippingCalculator)
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readToShip = basket.canShipCompletely() // Mocks are only checks in the when: block

        then: "order can be shipped"
        readToShip

        2 * inventory.isProductAvailable( _, _) >> true // Underscore matches arguments

        // Expect the isEmpty() method to be called any number of times, and when it does, it should return false
        _ * inventory.isEmpty() >> false // Underscore matches number of invocations

        // Expect zero invocations for all other methods of all other classes when the test runs
        0 * _ // Underscore matches mocked classes

    }
}
