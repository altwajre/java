package com.company

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not

import spock.lang.Specification

import static spock.util.matcher.HamcrestSupport.expect
import static spock.util.matcher.HamcrestSupport.that

class ReadableTest extends Specification {

    // 4.28 Spock support for Hamcrest matchers
    def "trivial test with Hamcrest"(){

        given: "a list of products"
        List<String> products = ["camera", "laptop", "hifi"] // Creation of a list

        expect: "camera should be one of them"
        products hasItem("camera") // hasItem() matcher accepts a list and returns true if any element matches the argument.

        and: "hotdog is not one of them"
        products not(hasItem("hotdog")) // not() matcher takes an existing matcher and reverses its meaning.

    }

    // 4.29 Alternative Spock support for Hamcrest matchers
    def "trivial test with Hamcrest (alt)"(){

        given: "an empty list"
        List<String> products = new ArrayList<>()

        when: "it is filled with products"
        products.add("laptop")
        products.add "camera"
        products.add "hifi"

        then: "camera should be one of them"
        expect(products, hasItem("camera")) // expect() is useful for then: blocks

        and: "hotdog is not one of them"
        that(products, not(hasItem("hotdog"))) // that() is useful for and: and expect: blocks
    }

    // 4.30 using Groovy closures in Spock assertions
    def "trivial test with Groovy closure"(){
        given: "a list of products"
        List<String> products = ["camera", "laptop", "hifi"]

        expect: "camera should be one of them"
        products.any{ name -> name == "camera" } // Iterates over list and passes if any is named camera

        and: "hotdog is not one of them"
        products.every{ name -> name != "hotdog" } // Iterates over list and checks all names of products

    }

    // 4.34 Grouping similar code with Groovy and Spock
    def "Buying products reduces the inventory availability"(){
        given: "an inventory with products"
        Product laptop = new Product(name: "toshiba", price: 1200, weight: 5)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        WarehouseInventory warehouseInventory = new WarehouseInventory()

        // Group object setup with Groovy object.with
        warehouseInventory.with {
            preload(laptop, 3)
            preload(camera, 5)
            preload(hifi, 2)
        }

        and: "an empty basket"
        EnterprisyBasket basket = new EnterprisyBasket()

        // Group object setup with Groovy object.with
        basket.with {
            setWarehouseInventory(warehouseInventory)
            setCustomerResolver(new DefaultCustomerResolver())
            setLanguage("english")
            setNumberOfCaches 3
            enableAutoRefresh()
        }

        when: "user gets a laptop and two cameras"
        basket.with {
            addProduct camera, 2
            addProduct laptop
        }

        and: "user complete the transaction"
        basket.checkout()

        then: "warehouse is updated accordingly"
        // Group assertions with Spock Specification.with
        with(warehouseInventory){
            !isEmpty()
            getBoxesMovedToday() == 3
            availableOfProduct("toshiba") == 2
            availableOfProduct("panasonic") == 3
            availableOfProduct("jvc") == 2
        }
    }

}
