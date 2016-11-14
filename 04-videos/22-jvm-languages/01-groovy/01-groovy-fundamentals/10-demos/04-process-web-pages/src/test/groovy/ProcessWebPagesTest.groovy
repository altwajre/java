import org.junit.Test

class ProcessWebPagesTest {
    @Test
    void text_eachline_test(){
        'http://oreilly.com'.toURL().text.eachLine { println it }
    }

    @Test
    void eachline_test(){
        'http://oreilly.com'.toURL().eachLine { println it }
    }

    @Test
    void readlines_size_test(){
        assert 'http://oreilly.com'.toURL().readLines()*.size() ==
                'http://oreilly.com'.toURL().text.readLines()*.size()
    }
}
