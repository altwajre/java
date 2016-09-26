package com.company.spock

import com.company.WordDetector
import spock.lang.Specification

// Groovy can covert everything to a Boolean variable
class WordDetectorTest extends Specification {
    def "demo for Groovy truth"() {
        when: "a line of text is processed"
        WordDetector wordDetector = new WordDetector();
        wordDetector.parseText("Understanding is a three edged sword: your side, their side, and the truth")

        then: "word frequency should be correct"
        wordDetector.wordsFound() > 0 // explicit conversion to Boolean
        wordDetector.duplicatesFound().size() > 0 // explicit conversion to Boolean

        wordDetector.wordsFound() // automatic "casting" to true/false (Groovy)
        wordDetector.duplicatesFound() // automatic "casting" to true/false (Groovy)
    }
}
