package com.company

import spock.lang.Specification

// Using Expando to mock interfaces
class MockTest extends Specification {
    def "Testing invalid address detection"(){

        when: "an address does not have a postcode"
        Address address = new Address(country: "Greece", number: 23) // Create the test data

        def dummyAddressDao = new Expando() // Mock - Creating the empty Groovy dynamic object
        dummyAddressDao.load = { return address } // Creating the load method dynamically

        Stamper stamper = new Stamper(dummyAddressDao as AddressDao) // Using the Groovy dynamic object in place of the Java interface

        then: "this address is rejected"
        !stamper.isValid(1) // Tricking the class under test to use the Expando-the argument is irrelevant

    }

    def "Testing invalid and valid address detection"(){

        when: "two different addresses are checked"
        Address invalidAddress = new Address(country: "Greece", number: 23)
        Address validAddress = new Address(country: "Greece", number: 23, street: "Argous", postCode: "4534")

        def dummyAddressDao = new Expando() // Mock
        dummyAddressDao.load = { id -> return id == 2 ? validAddress : invalidAddress}

        Stamper stamper = new Stamper(dummyAddressDao as AddressDao)
        then: "Only the address with street and postcode is accepted"
        !stamper.isValid(1)
        stamper.isValid(2)

    }

    def "Use Expando as test-data generator"(){

        when:
        Expando smartIterator = new Expando() // Creating empty Groovy dynamic object
        smartIterator.counter = 0 // Creating field that will hold next number
        smartIterator.limit = 4 // Creating field that will hold max value returned
        smartIterator.hasNext = {return counter < limit} // Limitation of iterator interface method
        smartIterator.next = {return counter++}
        smartIterator.restartFrom = {from -> counter = from} // Adding custom method not defined in iterator interface

        for(Integer number : smartIterator as Iterator<Integer>){ // Using the Expando in the place of an iterator
            println "Next number is $number"
        }

        println "Reset smart iterator"
        smartIterator.restartFrom(2) // Calling the custom method to change the state of the iterator

        for(Integer number : smartIterator as Iterator<Integer>){ // Using the Expando after resetting it
            println "Next number is $number"
        }

        then:
        smartIterator

    }
}
