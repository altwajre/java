package com.company

import spock.lang.Specification

// 4.32 All Spock lifecycle methods
class SpockLifecycleMethods extends Specification {

    def setupSpec(){
        println "setupSpec() will run only once"
    }

    def setup(){
        println "setup() will run before EACH feature"
    }

    def "first feature being tested"(){
        expect: "trivial test"
        println "first feature runs"
        2 == 1 + 1
    }

    def "second feature being tested"(){
        expect: "trival test"
        println "second feature runs"
        5 == 3 + 2
    }

    def cleanup(){
        println "cleanup() will run once after EACH feature"
    }

    def cleanupSpec(){
        println "cleanupSpec() will once at the end"
    }
}
