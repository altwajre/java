import org.junit.Test

class iteration_test {
    @Test
    void iterate_list(){
        List nums = [3, 1, 4, 1]

        println "#forEach from Java"
        nums.forEach{s ->
            if (s == 3){
                System.out.println("wow");
            }
            else{
                System.out.println(s);
            }
        };

        println "#each"
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
#forEach from Java
wow
1
4
1
#each
wow
1
4
1
 */
}
