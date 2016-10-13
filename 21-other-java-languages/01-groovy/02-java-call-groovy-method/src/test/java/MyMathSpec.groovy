import spock.lang.Specification

class MyMathSpec extends Specification {
    def "add test" () {
        when:
        MyMath math = new MyMath()
        int result = math.add(1, 2)

        then:
        result == 3
    }
}
