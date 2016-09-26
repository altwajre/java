package com.company.spock

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
}
