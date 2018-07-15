import org.junit.Test

class StringOperatorsTest {

    @Test
    public void plus_test(){

        def s = 'this is a string'
        println s + ' and more'

    }
    /*
    output:
    this is a string and more
     */

    @Test
    public void minus_test(){

        def s = 'this is a string'
        println s - 'is' - 'is' // remove two "is"

    }
    /*
    output:
    th  a string
     */

    @Test
    public void times_test(){

        def s = 'abc'
        println s * 3

    }
    /*
    output:
    abcabcabc
     */

    @Test
    public void first_letter_test(){

        def s = 'this is a string'
        println s[0]

    }
    /*
    output:
    t
     */

    @Test
    public void last_letter_test(){

        def s = 'this is a string'
        println s[-1]

    }
    /*
    output:
    g
     */

    @Test
    public void range_test(){

        def s = 'this is a string'
        println s[0..3]

    }
    /*
    output:
    this
     */

    @Test
    public void reverse_test(){

        def s = 'this is a string'
        println s[-1..-3]

    }

}
