import org.junit.Test

class RangesTest {

    @Test
    public void ranges_test(){
        Range r = 1..10
        println r
        println r.from
        println r.to
        println r.contains(2)
        r = 1..<10
        println r
    }
    /*
    output:
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    1
    10
    true
    [1, 2, 3, 4, 5, 6, 7, 8, 9]
     */

    @Test
    public void lists_test(){

        List nums = [3, 1 , 4, 1, 5, 9, 2, 6, 5]
        println nums
        println nums.class.name
        println nums.drop(3) // drop the first 3
        println nums[1..3]
        println nums - 1 - 5 // remove 1s and 5s
        println nums << [3, 5] // append array
        println([3, [1, 4], 8].flatten())

    }
    /*
    output:
    [3, 1, 4, 1, 5, 9, 2, 6, 5]
    java.util.ArrayList
    [1, 5, 9, 2, 6, 5]
    [1, 4, 1]
    [3, 4, 9, 2, 6]
    [3, 1, 4, 1, 5, 9, 2, 6, 5, [3, 5]]
    [3, 1, 4, 8]
     */

}
