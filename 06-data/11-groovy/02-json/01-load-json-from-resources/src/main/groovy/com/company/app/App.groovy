package com.company.app

import groovy.json.JsonSlurper

class App {
    static void main(String... args){
        def jsonObject = new JsonSlurper().parse(new File('src/main/resources/json/employee-data.json')) // Creating the JsonSlurper object
        println jsonObject.staff.department.name // key value
        println jsonObject.staff.department.employee // array
        println jsonObject.staff.department.employee.size() // array size
        println jsonObject.staff.department.employee[0] // array[0] element
        println jsonObject.staff.department.employee[0].firstName // array[0] key value
    }
}
/*
output:
sales
[[age:24, firstName:Orlando, lastName:Boren], [age:28, firstName:Diana, lastName:Colgan]]
2
[age:24, firstName:Orlando, lastName:Boren]
Orlando
 */
