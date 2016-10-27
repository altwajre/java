import org.junit.Test

class DowntoTest {

    @Test
    public void java_downto_test() {
        10.downto(7, { println it })
    }
    /*
    output:
    10
    9
    8
    7
     */

    @Test
    public void downto_test() {
        10.downto(7) { println it }
    }
    /*
    output:
    10
    9
    8
    7
     */

}
