package com.company.app.test

import org.testng.annotations.Test
import spock.lang.Specification

@Test
class SpockTest extends Specification {

    def "Spock test"(){
        when:
        String foo = "foo"
        println foo

        then:
        foo

        when:
        String bar = "bar"
        println bar

        then:
        bar

    }
}
