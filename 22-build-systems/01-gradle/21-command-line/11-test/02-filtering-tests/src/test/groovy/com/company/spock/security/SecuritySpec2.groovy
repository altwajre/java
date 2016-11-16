package com.company.spock.security

import spock.lang.Specification

class SecuritySpec2 extends Specification {
    def "Security Spec 2"(){
        when:
        String test = "Smoke 2"

        then:
        test
    }
}
