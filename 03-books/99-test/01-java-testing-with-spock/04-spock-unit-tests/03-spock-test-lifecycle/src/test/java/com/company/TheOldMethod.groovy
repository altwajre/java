package com.company

import spock.lang.Specification

// 4.23 Asserting with the old() method
class TheOldMethod extends Specification {

    def "Adding a method product to the basket increases its weight"(){
        given: "an empty basket"
        Basket basket = new Basket()

        and: "a tv is already added to the basket"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        basket.addProduct tv // Product is added in given: block

        when: "user gets a camera too"
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        basket.addProduct(camera) // Second product is added in when: block

        then: "basket weight is updated accordingly"
        // use old() to check before the when: block, plus the weight camera weight
        basket.currentWeight == old(basket.currentWeight) + camera.weight // Checking the difference in weight

    }
}
