import org.junit.Test

class NumbersTest {

    @Test
    public void integer_floating_point_types_test(){
        println 3.getClass().getName()
        println 3.14.getClass().getName()
        println 3/5
    }
    /*
    output:
    java.lang.Integer
    java.math.BigDecimal
    0.6
     */

}
