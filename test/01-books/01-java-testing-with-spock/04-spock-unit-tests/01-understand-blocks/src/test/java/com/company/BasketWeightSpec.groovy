package com.company

import spock.lang.Specification

class BasketWeightSpec extends Specification {

    // # simple given-when-then
    def "Adding two and three results in 5"(){
        given: "the integers two and three"
        int a = 3
        int b = 2

        when: "they are added"
        int result = a + b

        then: "the result is five"
        result == 5
    }

    // # given-when-then
    def "4.3 given-when-then - A basket with one product has equal weight"(){
        given: "an empty basket and a TV" // The given: block sets up the scene for the test
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the TV" // Trigger the action that will be tested
        basket.addProduct(tv)

        then: "basket weight is equal to the TV" // Examine the results
        basket.currentWeight == tv.weight
    }

    // # setup: block
    def "4.4 setup: block - A basket with one product has equal weight (alternative)"(){
        setup: "an empty basket and a TV" // The setup: block sets up the scene for the test
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the TV" // Trigger the action that will be tested
        basket.addProduct(tv)

        then: "basket weight is equal to the TV" // Examine the results
        basket.currentWeight == tv.weight
    }

    // # and: block splits given
    // Use and: block to distinguish between the class under test (the Basket class) and the collaborators (the products)
    def "4.8 and: splits given: - A basket with three produces weights as their sum"(){
        given: "an empty basket" // given: block deals only with the class under test
        Basket basket = new Basket()

        and: "several products" // and: block creates the collaborators
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)

        when: "user wants to buy the TV and the camera and the hifi"
        basket.addProduct tv
        basket.addProduct camera
        basket.addProduct hifi

        then: "basket weight is equal to all product weights"
        basket.currentWeight == (tv.weight + camera.weight + hifi.weight)
    }

    // # and: block splits when
    def "4.9 and: splits when: - A basket with three produces weights as their sum"(){
        given: "an empty basket" // given: block deals only with the class under test
        Basket basket = new Basket()

        and: "several products" // and: block creates the collaborators
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)

        when: "user wants to buy the TV.."
        basket.addProduct tv

        and: "..the camera.."
        basket.addProduct camera

        and: "..and the wifi"
        basket.addProduct hifi

        then: "basket weight is equal to all product weights"
        basket.currentWeight == (tv.weight + camera.weight + hifi.weight)
    }

    // # and: block splits then
    def "4.10 and: splits then: - A basket with three produces weights as their sum"(){
        given: "an empty basket" // given: block deals only with the class under test
        Basket basket = new Basket()

        and: "several products" // and: block creates the collaborators
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)

        when: "user wants to buy the TV.."
        basket.addProduct tv

        and: "..the camera.."
        basket.addProduct camera

        and: "..and the wifi"
        basket.addProduct hifi

        then: "basket weight is equal to all product weights"
        basket.currentWeight == (tv.weight + camera.weight + hifi.weight)

        and: "it contains 3 products"
        basket.productTypesCount == 3
    }

    // # expect: block replaces given:, when:, and then: blocks
    def "4.11 expect: replaces given-when-then - An empty basket has no weight"(){
        expect: "zero weight when nothing is added" // Only the expect block is present
        new Basket().currentWeight == 0
    }

    // # expect: block replaces when:, and then: blocks
    def "4.12 expect: replaces when-then - An empty basket has no weight"(){
        given: "an empty basket"
        Basket basket = new Basket()

        expect: "that the weight is 0"
        basket.currentWeight == 0
    }

    // # expect: block performs intermediate assertion
    def "4.13 expect: for preconditions - A basket with two products weights as their sum (precondition)"(){
        given: "an empty basket, a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        expect: "that nothing should be inside" // expect: block performs intermediate assertion
        basket.currentWeight == 0
        basket.productTypesCount == 0

        when: "user wants to buy the TV and the camera"
        basket.addProduct tv
        basket.addProduct camera

        then: "basket weight is equal to both camera and tv" // then: block examines the final result
        basket.currentWeight == (tv.weight + camera.weight)
    }

    // # cleanup: to release resources even if test fails
    def "4.14 cleanup: release resources - A basket with one product has equal weight"(){
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the TV"
        basket.addProduct(tv)

        then: "basket weight is equal to the TV"
        basket.currentWeight == tv.weight

        cleanup: "refresh basket resources" // cleanup: block will always run, even if then: fails
        basket.clearAllProducts() // then: block examines the final result
    }
}
