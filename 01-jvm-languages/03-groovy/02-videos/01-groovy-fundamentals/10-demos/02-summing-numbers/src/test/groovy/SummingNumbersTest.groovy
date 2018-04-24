import org.junit.Test

// times 2 each item and then sum
class SummingNumbersTest {
    @Test
    void java_double_sum_test(){
        List nums = [3, 1, 4, 1, 5, 9, 2, 6, 5]

        int total = 0
        for (int n : nums){
            total += 2 * n
        }
        println total
    }
    /*
    output:
    72
     */

    @Test
    void collect_double_sum_test(){
        List nums = [3, 1, 4, 1, 5, 9, 2, 6, 5]
        println nums.collect{ it * 2 }.sum()
    }

    @Test
    void closure_double_sum_test(){
        List nums = [3, 1, 4, 1, 5, 9, 2, 6, 5]
        println nums.sum{ it * 2 } // equivalent to the collect()
    }

    @Test
    void spread_dot_double_sum_test(){
        List nums = [3, 1, 4, 1, 5, 9, 2, 6, 5]
        println nums.multiply(2).sum()
    }

    @Test
    void inject_test(){
        List nums = [3, 1, 4]
        def result = nums.inject(0) { acc, val ->
            println "acc=$acc, val=$val"
            acc + val
        }
        println "result=$result"
    }
    /*
    output:
    acc=0, val=3
    acc=3, val=1
    acc=4, val=4
    result=8
     */

    @Test
    void inject_double_sum_test(){
        List nums = [3, 1, 4, 1, 5, 9, 2, 6, 5]
        println nums.inject(0) { acc, val ->
            acc + 2 * val
        }
    }

}
