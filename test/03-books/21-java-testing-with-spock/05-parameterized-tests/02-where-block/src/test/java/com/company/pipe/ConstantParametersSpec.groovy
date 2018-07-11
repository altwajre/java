package com.company.pipe

import com.company.Calculator
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ConstantParametersSpec extends Specification {

    // 5.16 Constant parameters in Spock tests
    def "Multipling #first and #second is always a negative number "(){

        given: "a calculator"
        Calculator calc = new Calculator()

        expect: "that multiplying a positive and negative number results in a negative number"
        calc.multiply(first, second) < 0

        where: "some scenarios are"
        first << [20, 34, 44, 67]
        second = -1

    }

    // 5.17 Derived parameters in Spock tests
    def "Multipling #first and #second is always a negative number (alt)"(){

        given: "a calculator"
        Calculator calc = new Calculator()

        expect: "that Multipling a positive and negative number results in a negative number"
        calc.multiply(first, second) < 0

        where: "some scenarios are"
        first << [20,34,44,67] // This parameter is explicitly defined; "first" is an integer
        second = first * -1 // This parameter depends on another one; "second" is the first with minus sign

    }


}
