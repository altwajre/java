package com.company.app.stub

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

/*
The Spock uses the underscore (_) as the role of "I don't care what goes in here.
 */
@Subject(Basket.class)
class MatchArgumentStubSpec extends Specification {

    // 6.6 Using argument matchers in stubs
    def "If warehouse has both products everything is fine"(){

        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with enough stock"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_, 1) >> true // Stubbing a method call regardless of the value of an argument
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv and the camera"
        basket.addProduct tv
        basket.addProduct camera


        then: "order can be shipped right away"
        basket.canShipCompletely()

    }

    // 6.7 Ignoring all arguments of a stubbed method when returning a response
    def "If warehouse is fully stocked everything is fine"(){

        given: "a basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        // Instruct my warehouse to answer that the product is always in stock, regardless of the product
        // Don't care if the class under test asks for a TV or a camera; it will always be in stock
        inventory.isProductAvailable(_, _) >> true
        basket.setWarehouseInventory(inventory)

        when: "user checks out multiple products"
        basket.addProduct tv, 33
        basket.addProduct camera, 12

        then: "order can be shipped right away"
        basket.canShipCompletely()

    }

}
