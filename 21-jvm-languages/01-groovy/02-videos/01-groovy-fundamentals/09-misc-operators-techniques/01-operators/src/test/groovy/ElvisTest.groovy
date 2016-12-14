import org.junit.Test

class ElvisTest {
    @Test
    void non_elvis_test(){
        String name
        String n = (name != null && name.size() > 0 ? name : 'World')
        println "Hello, $n!"
    }
    /*
    output:
    Hello, World!
     */

    // Elvis operator - ?:
    @Test
    void elvis_test(){
        String name
//        String n = name ? name : 'World'
        String n = name ?: 'World'
        println "Hello, $n!"
    }
    /*
    output:
    Hello, World!
     */

}
