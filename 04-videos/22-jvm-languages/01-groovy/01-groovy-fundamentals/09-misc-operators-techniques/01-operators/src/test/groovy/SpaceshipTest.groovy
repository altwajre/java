import org.junit.Test

class SpaceshipTest {
    // <=> works for Comparable interface
    @Test
    void spaceship_test(){
        int x = 3
        int y = 6
        int z = 8
        println x <=> y
        println y <=> y
        println z <=> y
    }
    /*
    output:
    -1
    0
    1
     */


}
