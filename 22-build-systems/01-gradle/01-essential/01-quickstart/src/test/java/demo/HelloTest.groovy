package demo

import spock.lang.Specification

class HelloTest extends Specification {
    def "SayHello"() {
        expect: hello == "Hello, Gradle!"
        where: hello = new Hello().sayHello()
    }
}
