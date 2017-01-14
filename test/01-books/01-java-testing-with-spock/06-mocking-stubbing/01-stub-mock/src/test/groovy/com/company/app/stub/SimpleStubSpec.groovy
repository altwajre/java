package com.company.app.stub

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

/*
The real code of warehouse inventory never runs.
The Spock unit tests shown can run on their own, regardless of the status of the real inventory.
 As long as the signature of the warehouse class stays the same, these unit tests will continue to run correctly.
 */
@Subject(Basket.class)
class SimpleStubSpec extends Specification {

    // 6.2 Creating a simple stub with spock
    def "If warehouse is empty nothing can be shipped"(){

        given: "a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "an empty warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory) // Creates Spock stub
        inventory.isEmpty() >> true // Instructs the stub to return true when isEmpty() is called
        basket.setWarehouseInventory(inventory) // Injects the stub into the class under test

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely() // Calls the stub behind the scenes

    }

    // 6.3 Stubbing specific arguments
    def "If warehouse has the product on stock everything is fine"(){

        given: "a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with enough stock"
        WarehouseInventory inventory = Stub(WarehouseInventory) // Creating a Spock stub
        inventory.isProductAvailable("bravia",1) >> true // Instructing the stub to return true when specific arguments are used
        inventory.isEmpty() >> false // Instructing the warehouse to respond with false
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order can be shipped right away"
        basket.canShipCompletely()

    }

    def "If warehouse does not have all products, order cannot be shipped"(){

        given: "a basket, a TV add a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with partial availability"
        WarehouseInventory inventory = Stub(WarehouseInventory)

        // Different stub results depending on the argument
        inventory.isProductAvailable("bravia", 1) >> true
        inventory.isProductAvailable("panasonic", 1) >> false

        inventory.isEmpty() >> false
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera

        then: "order cannot be shipped right away"
        !basket.canShipCompletely()

    }

    // 6.5 Grouping all stubbed methods
    def "If warehouse does not have all products, order cannot be shipped (with)"(){

        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with partial availability"
        WarehouseInventory inventory = Stub(WarehouseInventory){
            // Compact way of stubbing methods
            isProductAvailable("bravia", 1) >> true
            isProductAvailable("panasonic", 1) >> false
        }
        basket.warehouseInventory = inventory // Setter injection using Groovy style

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera

        then: "order cannot be shipped right away"
        !basket.canShipCompletely()

    }

}
