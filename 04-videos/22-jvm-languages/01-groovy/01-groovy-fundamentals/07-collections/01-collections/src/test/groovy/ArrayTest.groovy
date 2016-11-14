import org.junit.Test

class ArrayTest {
    @Test
    public void array_test(){
        def strings = 'this is a list of string'.split()
        println strings
        println strings.class.name
    }
    /*
    output:
    [this, is, a, list, of, string]
    [Ljava.lang.String;
     */

    @Test
    public void list_test(){
        List list = 'this is a list of strings'.split()
        println list
        println list.class.name
    }
    /*
    output:
    [this, is, a, list, of, strings]
    java.util.ArrayList
     */

}
