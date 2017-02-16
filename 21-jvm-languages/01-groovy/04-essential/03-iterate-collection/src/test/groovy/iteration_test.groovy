import org.junit.Test

class iteration_test {
    @Test
    void iterate_list(){
        List nums = [3, 1, 4, 1]
        nums.each {
            if(it == 3){
                println 'wow'
            }
            else{
                println it
            }
        }
    }
/*
output:
wow
1
4
1
 */
}
