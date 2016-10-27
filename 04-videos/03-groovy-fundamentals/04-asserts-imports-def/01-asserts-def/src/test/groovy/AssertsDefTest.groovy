import org.junit.Test

class AssertsDefTest {

    @Test
    public void asserts_test(){
        int x = 3
        int y = 4
        assert 7 == x + y
    }

    @Test
    public void def_test(){
        def x = 1
        println x.getClass().getName()

        x = 'abc'
        println x.getClass().getName()

        x = new Date()
        println x.getClass().getName()
    }
    /*
    output:
    java.lang.Integer
    java.lang.String
    java.util.Date
     */

}
