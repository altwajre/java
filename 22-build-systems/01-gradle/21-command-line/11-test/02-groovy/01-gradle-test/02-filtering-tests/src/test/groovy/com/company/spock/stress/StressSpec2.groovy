package com.company.spock.stress

import spock.lang.Specification

class StressSpec2 extends Specification {
    def "Stress Spec 2"(){
        when:
        String test = "Stress 2"

        then:
        test
    }
}
