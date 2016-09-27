package com.company

import spock.lang.Specification

class ClosureTest extends Specification {
    def "Closure test"(){

        when:
        Closure closure = { int x -> return x * 2 } // A closure with full Groovy notation that doubles its integer argument
        assert closure(3) == 6 // Using the closure as a method

        def myClosure = { x -> x * 2 } // Same closure with concise Groovy. Return is optional as well
        myClosure(3) == 6

        def adder = { x, y -> x + y } // A closure with two arguments
        assert adder(3, 5) == 8

        then:
        closure
    }

    def "Testing Jpeg files"() {

        when: "only jpeg files are selected from a list of filenames"
        FileExtensionFilter myFilter = new FileExtensionFilter() // Creation of Java class
        myFilter.addValidExtension("jpeg") // Setup file extensions that will be accepted
        myFilter.addValidExtension("jpg")

        // List that will be passed to class under test
        List<String> testInput = ["image1.jpg", "image2.png", "image2.jpeg", "image4.gif", "image5.jpg", "image6.tiff"]
        List<String> result = myFilter.filterFileNames(testInput) // Result of method call is another list
        println result

        then: "result should not contain other types"
        // Using a closure to test each element of the list
        result.every{ filename -> filename.endsWith("jpeg") || filename.endsWith("jpg")}

    }
}
