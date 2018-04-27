package com.company.spock.smoke

import spock.lang.Specification

class SmokeSpec1 extends Specification {
    def "Smoke Spec 1"(){
        when:
        String test = "Smoke 1"

        then:
        test
    }
}
