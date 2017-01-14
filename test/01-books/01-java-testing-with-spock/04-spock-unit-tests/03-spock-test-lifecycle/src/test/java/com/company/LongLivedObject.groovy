package com.company

import spock.lang.Shared
import spock.lang.Specification

/*
Objects survive across all test methods by using @Shared
4.22 Using the @Shared annotation
 */
class LongLivedObject extends Specification {

    /*
    CreditCardProcessor is an expensive object. It connects to a bank back end and allows your basket to charge credit cards.
    So it will be created only once
     */
    @Shared
    CreditCardProcessor creditCardProcessor

    BillableBasket basket // Will be created multiple times

    def setupSpec(){
        creditCardProcessor = new CreditCardProcessor() // Expensive/slow initialization
    }

    def setup(){
        basket = new BillableBasket()
        creditCardProcessor.newDayStarted()
        basket.setCreditCardProcessor(creditCardProcessor)
    }

    def "user buys a single product"(){
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)

        and: "user wants to buy the TV"
        basket.addProduct tv

        when: "user checkout out"
        basket.checkout()

        then: "revenue is the same as the price of TV"
        creditCardProcessor.currentRevenue == tv.price
    }

    def "user buys two products"(){
        given: "an empty basket and a camera"
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)

        and: "user wants to two cameras"
        basket.addProduct(camera, 2)

        when: "user checks out"
        basket.checkout()

        then: "revenue is the same as the price of both products"
        creditCardProcessor.currentRevenue == 2 * camera.price
    }

    def cleanup(){
        basket.clearAllProducts()
    }

    def cleanupSpec(){
        creditCardProcessor.shutDown()
    }

}
