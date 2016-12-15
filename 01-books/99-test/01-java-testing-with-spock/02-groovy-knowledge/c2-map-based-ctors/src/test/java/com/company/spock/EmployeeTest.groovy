package com.company.spock

import com.company.Department
import com.company.Employee
import spock.lang.Specification

class EmployeeTest extends Specification {
    def "demo for quick constructors"() {
        when:
        Employee trainee = new Employee(age: 22, firstName: "Alice", lastName: "Olson", inTraining: true)
        Employee seasoned = new Employee(middleName: "Jones", lastName: "Corwin", age: 45, firstName: "Alex")

        List<Employee> people = Arrays.asList(trainee, seasoned)

        Department department = new Department()
        department.assign(people)

        then:
        department.manpowerCount() == 2
    }

    def "demo for quick constructors and lists"(){
        when:
        List<Employee> people = [
                new Employee(age: 22, firstName: "Alice", lastName: "Olson", inTraining: true),
                new Employee(middleName: "Jones", lastName: "Corwin", age: 45, firstName: "Alex")
        ]

        Department department = new Department()
        department.assign(people)

        then:
        department.manpowerCount() == 2
    }
}
