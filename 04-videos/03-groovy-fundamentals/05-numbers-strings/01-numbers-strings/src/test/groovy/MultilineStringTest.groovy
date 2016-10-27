import org.junit.Test

// Multiple Strings - good for SQL
class MultilineStringTest {

    // Single quote - no evaluation
    @Test
    public void single_quote_test() {
        def multilineString =
                '''first line
second line
third line
'''
        println multilineString

        println multilineString.readLines()

        println multilineString.readLines().size()
    }
    /*
    output:
    first line
    second line
    third line

    [first line, second line, third line]
    3
     */

    @Test
    public void back_slash_escape_test(){
        def multilineString =
                '''\
first line
second line
third line
'''
        println multilineString.readLines().size()
    }
    /*
    output:
    3
     */

    // Double quote - evaluate expression
    @Test
    public void double_quote_test(){
        def multilineString = """first line ${0 + 1}
second line ${0 + 2}
third line ${0 + 3}
"""
        println multilineString
    }
    /*
    output:
    first line 1
    second line 2
    third line 3
     */

    // Slashy - for Regular Expressions
    @Test
    public void slashy_test(){
        def zip = /\d{5}(-\d{4})?/
        println zip.getClass().getName()
        assert '12345' ==~ zip
        assert '12345-1234' ==~ zip
        assert '12345 12345-1234 1234'.findAll(zip) == ['12345', '12345-1234']
    }
    /*
    output:
    java.lang.String
     */

    @Test
    public void replaceAll_test(){
        def testString = 'Flee to me, remote elf!'.toLowerCase().replaceAll(/\W/,'')
        println testString
        println testString.reverse() // palindrome string
    }
    /*
    output:
    fleetomeremoteelf
    fleetomeremoteelf
     */

}
