package com.company.spock

import groovy.json.JsonSlurper
import spock.lang.Specification

class ReadFileTest extends Specification {
    def "Reading a text file"(){

        when:
        // Reading the whole text file
        println '# Reading the whole text file'
        String testInput = new File("src/test/resources/quotes.txt").text
        println testInput + '\n'

        // Reading a file line by line
        println '# Reading a file line by line'
        List<String> inputText = new File("src/test/resources/quotes.txt").readLines()
        for(String line : inputText){
            println line
        }

        then:
        testInput

    }

    def "Reading an XML file"(){

        when:

        def xmlRoot = new XmlSlurper().parse('src/test/resources/employee-data.xml') // Creating the XmlSlurper object
        assert xmlRoot.department.size() == 1 // Checking the number of children XML nodes
        assert xmlRoot.department.@name == "sales" // Accessing an XML attribute
        assert xmlRoot.department.employee.size() == 2 // Checking the number of children XML nodes
        assert xmlRoot.department.employee[0].firstName == "Orlando" // Accessing XML content of the first child
        assert xmlRoot.department.employee[0].lastName == "Boren"
        assert xmlRoot.department.employee[0].age == 24

        then:
        xmlRoot
    }

    def "Reading JSON in Groovy"(){
        when:
        def jsonRoot = new JsonSlurper().parse(new File('src/test/resources/employee-data.json')) // Creating the JsonSlurper object
        assert jsonRoot.staff.department.name == "sales" // Accessing json field
        assert jsonRoot.staff.department.employee.size() == 2 // Checking the size of JSon array
        assert jsonRoot.staff.department.employee[0].firstName == "Orlando" // Accessing first element of the array
        assert jsonRoot.staff.department.employee[0].lastName == "Boren"
        assert jsonRoot.staff.department.employee[0].age == "24"
        assert jsonRoot.staff.department.employee[1].firstName == "Diana" // Accessing second element of the array
        assert jsonRoot.staff.department.employee[1].lastName == "Colgan"
        assert jsonRoot.staff.department.employee[1].age == "28"

        then:
        jsonRoot
    }
}
