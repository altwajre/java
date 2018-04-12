import org.junit.Test

class IteratingListTest {

    @Test
    public void each_with_it_test(){
        List nums = [3, 1, 4, 1, 5, 9]
        nums.each { println it }
    }
    /*
    output:
    3
    1
    4
    1
    5
    9
     */

    @Test
    public void each_with_custom_var_name_test(){
        List nums = [3, 1, 4, 1, 5, 9]
        nums.each { n -> println n }
    }
    /*
    output:
    3
    1
    4
    1
    5
    9
     */

    @Test
    public void eachwithindex_test(){
        List nums = [3, 1, 4, 1, 5, 9]
        nums.eachWithIndex { int entry, int i ->
            println "nums[$i] == $entry" }
    }
    /*
    output:
    nums[0] == 3
    nums[1] == 1
    nums[2] == 4
    nums[3] == 1
    nums[4] == 5
    nums[5] == 9
     */

}
