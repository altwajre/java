package com.company.spock

import com.company.Department
import com.company.Employee
import spock.lang.Specification

class ListTest extends Specification {

    def "Groovy List"() {

        when:
        // Java
        List<String> javaRaces = Arrays.asList("Drazi", "Minbari", "Humans")

        // Groovy
        List<String> groovyRaces = ["Drazi", "Minbari", "Humans"]

        assert javaRaces == groovyRaces

//        String[] javaArray = { "Drazi", "Minbari", "Humans" }; // it works only in Java
        String[] groovyArray = ["Drazi", "Minbari", "Humans"]

        then:
        groovyRaces.size() == 3

    }

    def "Groovy list and maps"(){

        when:
        List<Employee> people = [
                new Employee(age: 22, firstName: "Alice", lastName: "Olson", inTraining: true),
                new Employee(middleName: "Jones", lastName: "Corwin", age: 45, firstName: "Alex")
        ]

        Department department = new Department()
        department.assign(people)

        then:
        people.size() == 2
    }

    def "Accessing lists in Groovy by using array index notation"(){

        when:
        List<String> humanShips = ["Condor", "Explorer"]
        assert humanShips.get(0) == "Condor" // Java
        assert humanShips[0] == "Condor" // Groovy

        humanShips.add("Java")  // Java
        humanShips << "g1" << "g2" // Groovy
        println humanShips
        assert humanShips[2] == "Java"
        assert humanShips[3] == "g1"

        humanShips[3] = "Updated Data" // Groovy way of replacing an element
        assert humanShips[3] == "Updated Data"
        println humanShips

        Map<String, String> personRoles = [:] // Creating an empty map in Groovy
        personRoles.put("Suzan Ivanova", "Lt. Commander") // Java way of inserting into map
        personRoles["Stephen Franklin"] = "Doctor" // Groovy way of inserting into map

        assert personRoles.get("Suzan Ivanova") == "Lt. Commander" // Java way of accessing map
        assert personRoles["Stephen Franklin"] == "Doctor" // Groovy way of accessing map

        personRoles["Suzan Ivanova"] = "Commander" // Groovy way of replacing element
        assert personRoles["Suzan Ivanova"] == "Commander"

        then:
        humanShips.size() == 5
    }
}
