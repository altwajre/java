package com.company.app.stub

import com.company.app.Basket
import com.company.app.Product
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket.class)
class SequentialStubSpec extends Specification {

    def "Inventory is always checked in the last possible moment"(){

        given: "a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with fluctuating stock levels"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", _) >>> true >> false
        inventory.isEmpty() >>> [false, true]
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cna be shipped right away"
        basket.canShipCompletely()

        when: "user wants another tv"
        basket.addProduct tv

        then: "order can no longer be shipped"
        !basket.canShipCompletely()

    }

}
