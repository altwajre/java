import groovy.transform.ToString
import org.junit.Test

@ToString
class Customer{
    String first
    String last
}
class AstTransformationTest {

    @Test
    public void customer_test(){

        Customer customer = new Customer(first: 'Tom', last: 'Lee')
        println customer.toString()

    }
}
