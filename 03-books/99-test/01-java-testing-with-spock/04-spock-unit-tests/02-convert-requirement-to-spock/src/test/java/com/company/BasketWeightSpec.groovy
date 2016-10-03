package com.company

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

// 4.2.3 Describing the Spock test as a whole
// 4.18 Writing a full Spock specification - @Narrative
@Narrative(""" An empty basket starts with no weight. Adding products to the basket increases its weight.
The weight is then used for billing during shipping calculations. Electronic goods have always zero weight. """)
// 4.17 writing a Spock specification - @Title
@Title("Unit test for basket weight")
class BasketWeightSpec extends Specification {

    // 4.2.1 Explaining the feature examined
    def "4.15 - test method describes - A basket with one product has equal weight"(){ // Full English text
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the TV"
        basket.addProduct tv

        then: "basket weight is equal to the TV"
        basket.currentWeight == tv.weight
    }

    // 4.2.2 Marking the class under test - @Subject
    def "4.16 -  marking class under test - A basket with two products weights as their sum"(){
        given: "an empty basket"
        @Subject  // The subject of this test is the Basket class
        Basket basket = new Basket()

        and: "several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)

        when: "user wants to buy the TV and the camera and the hifi"
        basket.addProduct tv
        basket.addProduct camera

        then: "basket weight is equal to all product weight"
        basket.currentWeight == (tv.weight + camera.weight)
    }

}
