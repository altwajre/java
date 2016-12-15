package com.company.simple

import com.company.Adder
import spock.lang.Specification

// 5.8 Lifecycle of parameterized tests
class LifecycleDataSpec extends Specification {

    // The when: and then: blocks are executed once for each scenario.
    def setup() {
        println "Setup prepares next run"
    }

    // Single test method runs multiple times
    def "Trivial adder test"() {

        given: "an adder"
        Adder adder = new Adder()
        println "Given: block runs"

        when: "the add method is called for two numbers"
        int result = adder.add(first, second)
        println "When: block runs for first = $first and second = $second"

        then: "the result should be the sum of them"
        result == sum
        println "Then: block is evaluated for sum = $sum"

        where: "some scenarios are"
        first | second || sum
        1     | 1      || 2
        3     | 2      || 5
        3     | -3     || 0

    }

    def cleanup() {
        println "Cleanup releases resources of last run\n"
    }
}
