class Person {
    String first
    String last
    void setLast(String last){
        println 'inside setLast()'
        this.last = last
    }
    String toString() { "toString: $first $last" }
}

Person qb = new Person()
qb.setFirst('Tom')
qb.last = 'Brady'
println "${qb.getFirst()} ${qb.last}"

// map base constructor
Person p = new Person(first: 'Dick', last: 'Lee')
println "${p.getFirst()} ${p.last}"
println p

import groovy.transform.*
@ToString // Abstract Syntax Tree transformation
class Customer{
    String first
    String last
}
Customer c = new Customer(first: 'Tom', last: 'Will')
println c
