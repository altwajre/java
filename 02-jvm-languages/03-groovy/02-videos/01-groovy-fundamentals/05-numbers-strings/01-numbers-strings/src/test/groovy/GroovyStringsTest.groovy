import org.junit.Test

class GroovyStringsTest {

    @Test
    public void groovy_strings_test(){
        def s = 'this is a string ${1 + 1}'
        println s
        println s.getClass().getName()

        s = "this is a string ${1 + 1}"
        println s
        println s.getClass().getName()
    }
    /*
    output:
    this is a string ${1 + 1}
    java.lang.String
    this is a string 2
    org.codehaus.groovy.runtime.GStringImpl
     */

}
