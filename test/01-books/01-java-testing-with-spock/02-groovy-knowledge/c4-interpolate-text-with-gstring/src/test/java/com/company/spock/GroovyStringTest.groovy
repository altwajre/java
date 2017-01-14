package com.company.spock

import com.company.groovy.Department
import com.company.groovy.Employee
import spock.lang.Specification

// Groovy string within double quotes
class GroovyStringTest extends Specification {
    def "Groovy Strings"() {

        when:
        Department department = new Department(name: "Sales", location: "block C")
        Employee employee = new Employee(fullName: "Andrew Collins", age: 37, department: department)

        println("Age is " + employee.getAge()) // Java way of accessing fields
        println "Age is $employee.age" // Groovy string

        println("Department location is at " + employee.getDepartment().getLocation()) // Java
        println "Department location is at $employee.department.location" // Groovy string

        println "Person is adult ${employee.age > 18}" // Groovy string - using {} for full expressions
        println "Amount in dollars is \$300" // Escaping the $ character
        println 'Person is adult ${employee.age > 18}' // Disabling evaluation altogether with single quotes

        then:
        department

    }

    def "Groovy multiline strings"() {

        when:
        String input = '''I want you to know you were right. I didn't want \
               to admit that. Just pride I guess. You get my age, you \
               get kinda set in your ways. It had to be \
               done. Don't blame yourself for what happened latter.'''

        then:
        input

    }
}
