package com.company.app.stub

import com.company.app.Basket
import com.company.app.stubs.EnterprisyBasket
import com.company.app.Product
import com.company.app.stubs.ServiceLocator
import com.company.app.stubs.WarehouseInventory
import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket.class)
class StubsInStubsSpec extends Specification {

    // 6.12 Stubbing responses with other stubs
    def "If warehouse is empty nothing can be shipped"(){

        given: "a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)

        and: "an empty warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory) // Stub that will be used by the class under test
        inventory.isEmpty() >> true
        ServiceLocator serviceLocator = Stub(ServiceLocator) // Stubbing of intermediary class
        serviceLocator.getWarehouseInventory() >> inventory // $$ Instructing a stub to return another stub

        and: "a basket"
        EnterprisyBasket basket = new EnterprisyBasket(serviceLocator) // Using the parent stub in the class under test

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely()

    }
}
