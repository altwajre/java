package com.company.spock

import com.company.Adder
import com.company.Multiplier
import spock.lang.Specification

class MultiplierTest extends Specification {
    def "Multiply two numbers and return the result"(){
        when: "a new Multiplier class is created"
        def multi = new Multiplier()

        then: "3 times 7 is 21"
        multi.multiply(3, 7) == 21
    }

    def "Combine both multiplication and addition"() {
        when: "a new Multiplier and Adder classes are created"
        def adder = new Adder()
        def multi = new Multiplier()

        then: "4 times (2 plus 3) is 20"
        multi.multiply(4, adder.add(2, 3)) == 20

        and: "(2 plus 3) times 4 is also 20"
        multi.multiply(adder.add(2, 3), 4) == 20
    }
}

/*
output:
Condition not satisfied:

multi.multiply(4, adder.add(2, 3)) == 20
|     |           |     |          |
|     25          |     5          false
|                 com.company.Adder@229d10bd
com.company.Multiplier@2ddc8ecb

Expected :20

Actual   :25
*/
