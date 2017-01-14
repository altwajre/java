package com.company.app.mock

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket.class)
class ArgumentTypeVerificationSpec extends Specification {

    // 6.20 Verifying that arguments aren't null when a mocked method is called
    def "Warehouse is queried for each product - null"(){
        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory) // Creating a Spock mock
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can be shipped"
        readyToShip

        // expect isProductAvailable() will be called twice. The first argument can be anything other than null,
        // and the second argument will always be 1. When that happens, the method will return true.
        2 * inventory.isProductAvailable(!null, 1) >> true // Verifying that the first argument isn't null

    }

    // 6.21 Verifying the type of arguments
    def "Warehouse is queried for each product - type"() {
        given: "a basket, a TV and camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory) // Creating a Spock mock
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can be shipped"
        readyToShip

        // Verify that the first argument is always a string and the second always an integer
        2 * inventory.isProductAvailable(_ as String, _ as Integer) >> true

    }
}
