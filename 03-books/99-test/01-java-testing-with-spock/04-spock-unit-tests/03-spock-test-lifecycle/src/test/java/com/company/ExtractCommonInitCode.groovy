package com.company

import spock.lang.Specification

// 4.19 Extracting common initialization code
class ExtractCommonInitCode extends Specification {
    // 4.19 Extract common inti code
    // Common classes are placed as fields
    Product tv
    Product camera

    def setup(){ // This method runs before each test method
        // Initialization code
        tv = new Product(name: "bravia", price: 1200, weight: 18)
        camera = new Product(name: "panasonic", price: 350, weight: 2)
    }

    def "A basket with one product weights as that product"(){
        given: "an empty basket"
        Basket basket = new Basket()

        when: "user wants to buy the TV"
        basket.addProduct tv

        then: "basket weight is equal to the TV"
        basket.currentWeight == tv.weight
    }
}
