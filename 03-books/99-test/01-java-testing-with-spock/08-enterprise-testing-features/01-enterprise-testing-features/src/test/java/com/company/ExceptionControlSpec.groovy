package com.company

import spock.lang.Specification

class ExceptionControlSpec extends Specification {

    // 8.1 Expecting an exception in a Spock test
    def "Error conditions for unknown products"(){

        given: "a warehouse"
        WarehouseInventory inventory = new WarehouseInventory()

        when: "warehouse is queried for the wrong product"
        inventory.isProductAvailable("productThatDoesNotExist", 1)

        then: "an exception should be thrown"
        // This test will pass only if IllegalArgumentException is thrown in the when: block
        thrown(IllegalArgumentException)

    }

    // 8.2 Detailed examnination of an expected exception
    def "Error conditions for unknown products - better"(){

        given: "a warehouse"
        WarehouseInventory inventory = new WarehouseInventory()

        when: "warehouse is queried for the wrong product"
        inventory.isProductAvailable("productThatDoesNotExist", 1)

        then: "an exception should be thrown"
        // Keeps the exception thrown in the e variable
        IllegalArgumentException e = thrown()
        // The test will pass only if the exception contains a specific message
        e.getMessage() == "Unknown product productThatDoesNotExist"

    }

    // 8.3 Explicit declaration that an exception shouldn't happen
    def "Negative quantity is the same as 0"(){
        given: "a warehouse"
        WarehouseInventory inventory = new WarehouseInventory()

        and: "a product"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)

        when: "warehouse is loaded with a negative value"
        inventory.preload(tv, -5)

        then: "the stock is empty for that product"
        // Clarifies the intention of testing normal operation without exception
        notThrown(IllegalArgumentException)
        !inventory.isProductAvailable(tv.getName(), 1)
    }
}
