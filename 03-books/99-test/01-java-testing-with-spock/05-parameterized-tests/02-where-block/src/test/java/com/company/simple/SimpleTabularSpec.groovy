package com.company.simple

import com.company.Adder
import spock.lang.Specification

class SimpleTabularSpec extends Specification {

    // 5.4 Using data tables in Spock
    def "Trivial adder test"() {
        given: "an adder"
        Adder adder = new Adder()

        expect: "that it calculates the sum of two numbers"
        // Relationship between output and input parameters: sum is based on first and second
        adder.add(first,second)==sum

        where: "some scenarios are"
        // Name of parameters first and second are input and sum is output
        first   |second     || sum

        1       | 1         || 2
        3       | 2         || 5
        82      | 16        || 98
        3       | -3        || 0
        0       | 0         || 0
    }

    def "Trivial adder test (alt)"(int first, int second, int sum) {

        given: "an adder"
        Adder adder = new Adder()

        expect: "that it calculates the sum of two numbers"
        adder.add(first,second)==sum

        where: "some scenarios are"
        first   |second     || sum
        1       | 1         || 2
        3       | 2         || 5
        82      | 16        || 98
        3       | -3        || 0
        0       | 0         || 0
    }

}
