import spock.lang.Specification

class MySpec extends Specification {
    def "call static method test"(){
        when:
        MyUtils.update("hi")

        then:
        1
    }
}
