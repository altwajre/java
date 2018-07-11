package com.company.spock

import com.company.Address
import com.company.Employee
import spock.lang.Specification

class MapTest extends Specification {
    def "Groovy Map"() {
        when:
        // Java
        Map<String, Integer> javaWordCounts = new HashMap<>();
        javaWordCounts.put("Hello", 1);
        javaWordCounts.put("Java", 1);
        javaWordCounts.put("World", 2);

        // Groovy
        Map<String, Integer> wordCounts = ["Hello": 1, "Groovy": 1, "World": 2]

        then:
        wordCounts.size() == 3
    }

    def "Use object as key"(){
        when:
        Employee person1 = new Employee(firstName: "Alice", lastName: "Olson", age: 30)
        Employee person2 = new Employee(firstName: "Jones", lastName: "Corwin", age: 45)

        Address address1 = new Address(street: "Marley", number: 25)
        Address address2 = new Address(street: "Barnam", number: 7)

        Map<Employee, Address> javaAddresses = new HashMap<>();
        javaAddresses.put(person1, address1); // Java way
        javaAddresses.put(person2, address2); // Java way

        // Groovy way - need to use extra parentheses when objects are used for keys
        Map<Employee, Address> groovyAddresses = [(person1): address1, (person2): address2]

        then:
        groovyAddresses.size() == 2

    }
}
