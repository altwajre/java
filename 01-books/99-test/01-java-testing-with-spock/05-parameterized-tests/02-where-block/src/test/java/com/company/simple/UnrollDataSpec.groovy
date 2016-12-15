package com.company.simple

import com.company.Adder
import spock.lang.Specification
import spock.lang.Unroll

class UnrollDataSpec extends Specification {

    // 5.9 Unrolling parameterized scenarios
    @Unroll
    def "Trivial adder test"() {
        given: "an adder"
        Adder adder = new Adder()

        when: "the add method is called for two numbers"
        int result = adder.add(first,second)

        then: "the result should be the sum of them"
        result ==sum

        where: "some scenarios are"
        first   |second     || sum
        1       | 1         || 2
        3       | 2         || 5
        3       | -3        || 0
    }

    // 5.10 Printing parameters of each scenario
    @Unroll("Testing the Adder for #first + #second = #sum")
    def "Trivial adder test (alt2)"() {
        given: "an adder"
        Adder adder = new Adder()

        when: "the add method is called for two numbers"
        int result = adder.add(first,second)
        then: "the result should be the sum of them"
        result ==sum
        where: "some scenarios are"
        first   |second     || sum
        1       | 1         || 2
        3       | 2         || 5
        3       | -3        || 0
    }

    // 5.11 Parameter rendering on the test method
    @Unroll
    def "Testing the Adder for #first + #second = #sum "() {
        given: "an adder"
        Adder adder = new Adder()

        when: "the add method is called for two numbers"
        int result = adder.add(first,second)
        then: "the result should be the sum of them"
        result ==sum
        where: "some scenarios are"
        first   |second     || sum
        1       | 1         || 2
        3       | 2         || 5
        3       | -3        || 0
    }

}
