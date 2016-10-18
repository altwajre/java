package com.company.app.stub

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.ShippingCalculator
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket.class)
class DynamicStubSpec extends Specification {

    def "Basket handles shipping charges according to product count - static"(){

        given: "a basket and several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        Product laptop = new Product(name: "toshiba", price: 800, weight: 10)
        Basket basket = new Basket()

        and: "a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable( _, _) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator)
        shippingCalculator.findShippingCostFor(tv, 2) >> 20
        shippingCalculator.findShippingCostFor(camera, 2) >> 20
        shippingCalculator.findShippingCostFor(hifi, 1) >> 10
        shippingCalculator.findShippingCostFor(laptop, 3) >> 30
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        basket.addProduct tv, 2
        basket.addProduct camera, 2
        basket.addProduct hifi
        basket.addProduct laptop, 3

        then: "cost is correctly calculated"
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + 3 * laptop.price + basket.getProductCount() * 10

    }

    /*
    Using the Groovy closure, you're instrumented the shipping calculator stub with your selected pricing strategy in a single line of code.
    With that one line, the shipping calculator can respond to 1, 2, or 100 products added to the basket.
     */
    // 6.10 Stubs taht respond according to arguments
    def "Basket handles shipping charges according to product count - dynamic"(){

        given: "a basket and several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        Product laptop = new Product(name: "toshiba", price: 800, weight: 10)
        Basket basket = new Basket()

        and: "a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory) // Stubbing a concrete class
        inventory.isProductAvailable( _, _) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator) // Stubbing a Java interface

        // Using a Groovy closure for a dynamic response
        shippingCalculator.findShippingCostFor( _, _) >> {Product product, int count -> 10 * count}

        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        // Adding different quantities to the basket
        basket.addProduct tv, 2
        basket.addProduct camera, 2
        basket.addProduct hifi
        basket.addProduct laptop, 3

        then: "cost is correctly calculated"
        // Verify the shipping charges are included
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + 3 * laptop.price + basket.getProductCount() * 10

    }

    def "Downloadable goods do not have shipping cost - static"() {

        given: "a basket and several products"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        Product camera = new Product(name:"panasonic",price:350,weight:2)
        Product hifi = new Product(name:"jvc",price:600,weight:5)
        Product laptop = new Product(name:"toshiba",price:800,weight:10)
        Product ebook = new Product(name:"learning exposure",price:30,weight:0)
        Product suite = new Product(name:"adobe essentials",price:200,weight:0)
        Basket basket = new Basket()

        and: "a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable( _ , _) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each physical product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator)
        shippingCalculator.findShippingCostFor(tv,2) >> 20
        shippingCalculator.findShippingCostFor(camera,2) >> 20
        shippingCalculator.findShippingCostFor(hifi,1) >> 10
        shippingCalculator.findShippingCostFor(laptop, 1) >> 10
        shippingCalculator.findShippingCostFor( ebook,1) >> 0
        shippingCalculator.findShippingCostFor(suite, 3 ) >> 0
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        basket.addProduct tv,2
        basket.addProduct camera,2
        basket.addProduct hifi
        basket.addProduct laptop
        basket.addProduct ebook
        basket.addProduct suite,3

        then: "cost is correctly calculated"
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + laptop.price  +
                ebook.price + 3 * suite.price+ 60

    }

    // 6.11 A smart stub that looks at both its arguments
    def "Downloadable goods do not have shipping cost - dynamic"() {

        given: "a basket and several products"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        Product camera = new Product(name:"panasonic",price:350,weight:2)
        Product hifi = new Product(name:"jvc",price:600,weight:5)
        Product laptop = new Product(name:"toshiba",price:800,weight:10)
        Product ebook = new Product(name:"learning exposure",price:30,weight:0)
        Product suite = new Product(name:"adobe essentials",price:200,weight:0)
        Basket basket = new Basket()

        and: "a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable( _ , _) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each physical product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator) // Stubbing a Java interface
        // Groovy closure that uses both arguments
        shippingCalculator.findShippingCostFor( _, _) >> { Product product, int count -> product.weight == 0 ? 0 : 10 * count}
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        basket.addProduct tv,2
        basket.addProduct camera,2
        basket.addProduct hifi
        basket.addProduct laptop
        basket.addProduct ebook
        basket.addProduct suite,3

        then: "cost is correctly calculated"
        // 60 is shipping charges for the physical goods only.
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + laptop.price  +
                ebook.price + 3 * suite.price+ 60

    }

}
