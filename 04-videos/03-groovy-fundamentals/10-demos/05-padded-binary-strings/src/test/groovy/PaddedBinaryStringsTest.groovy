import org.junit.Test

class PaddedBinaryStringsTest {
    @Test
    void padded_binary_strings_test(){
        (0..15).each {
            println Integer.toBinaryString(it).padLeft(4, '0')
        }
    }
    /*
    output:
    0000
    0001
    0010
    0011
    0100
    0101
    0110
    0111
    1000
    1001
    1010
    1011
    1100
    1101
    1110
    1111
     */
}
