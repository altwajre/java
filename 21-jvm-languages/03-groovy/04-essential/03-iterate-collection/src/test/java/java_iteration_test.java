import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class java_iteration_test {
    @Test
    public void iterate_list(){
        List<Integer> nums = Arrays.asList(3, 1, 4, 1);

        nums.forEach(s -> {
            if (s == 3){
                System.out.println("wow");
            }
            else{
                System.out.println(s);
            }
        });

    }
}
