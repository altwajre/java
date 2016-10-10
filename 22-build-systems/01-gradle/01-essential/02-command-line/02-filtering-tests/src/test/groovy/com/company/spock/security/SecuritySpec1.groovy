package com.company.spock.security

import spock.lang.Specification

class SecuritySpec1 extends Specification {
    def "Security Spec 1"(){
        when:
        String test = "Security 1"

        then:
        test
    }
}
