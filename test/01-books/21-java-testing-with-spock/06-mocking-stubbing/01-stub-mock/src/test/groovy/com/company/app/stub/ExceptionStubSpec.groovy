package com.company.app.stub

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket.class)
class ExceptionStubSpec extends Specification {

    def "A problematic inventory means nothing can be shipped"(){

        given: "a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with serious issues"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", _) >> {throw new RuntimeException("critical error")}
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely()

    }

}
