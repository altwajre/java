package com.company.spock.smoke

import spock.lang.Specification

class SmokeSpec2 extends Specification {
    def "Smoke Spec 2"(){
        when:
        String test = "Smoke 2"

        then:
        test
    }
}
