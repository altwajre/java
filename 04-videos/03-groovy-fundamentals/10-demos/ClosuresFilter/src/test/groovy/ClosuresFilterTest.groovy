import org.junit.Test

class ClosuresFilterTest {
    @Test
    void findall_test(){
        println (-3..3).findAll { it > 0 }
    }
    /*
    output:
    [-3, -2, -1, 0, 1, 2, 3]
     */

}
