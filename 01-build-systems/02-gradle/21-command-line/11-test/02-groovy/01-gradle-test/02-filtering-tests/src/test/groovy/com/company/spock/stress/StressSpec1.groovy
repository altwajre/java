package com.company.spock.stress

import spock.lang.Specification

class StressSpec1 extends Specification {
    def "Stress Spec1"(){
        when:
        String test = "Stress 1"

        then:
        test
    }
}
