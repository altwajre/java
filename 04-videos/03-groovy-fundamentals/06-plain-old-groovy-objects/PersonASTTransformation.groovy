/**
 * AST - abstract syntax tree transformation
 */
import groovy.transform.*

@ToString
class Person {
    String first
    String last
}

Person p = new Person(first: 'Tom', last: 'Lee')
println p.toString()

//output:
//Person(Tom, Lee)
