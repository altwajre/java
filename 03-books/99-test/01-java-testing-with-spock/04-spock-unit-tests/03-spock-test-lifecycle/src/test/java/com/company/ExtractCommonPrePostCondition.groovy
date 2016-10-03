package com.company

import spock.lang.Specification
import spock.lang.Subject

// 4.20 Extracting common pre/post conditions
//@Subject(Basket) // Comment out due to intellij does not like it
class ExtractCommonPrePostCondition extends Specification {
    Product tv
    Product camera
    Basket basket

    def setup(){ // This method will run before each test method
        println "setup"
        tv = new Product(name: "bravia", price: 1200, weight: 18)
        camera = new Product(name: "panasonic", price: 350, weight: 2)
        basket = new Basket()
    }

    def "A basket with one product weights as that product"(){
        when: "user wants to buy the TV"
        basket.addProduct tv

        then: "basket weight is equal to all product weight"
        basket.currentWeight == tv.weight
    }

    def "A basket with two products weights as their sum"(){
        when: "user wants to buy the TV and the camera"
        basket.addProduct tv
        basket.addProduct camera

        then: "basket weight is equal to all product weight"
        basket.currentWeight == (tv.weight + camera.weight)
    }

    def cleanup(){ // This method will run after each test method
        println "cleanup"
        basket.clearAllProducts()
    }

}
