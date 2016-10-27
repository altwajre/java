import org.junit.Test

class TransformCollectionTest {

    @Test
    public void non_functional_double_integers_in_list_test() {
        List nums = [3, 1, 4, 1, 5, 9]
        List results = []
        nums.each {
            results << it * 2
        }
        println results
    }
    /*
    output:
    [6, 2, 8, 2, 10, 18]
     */

    @Test
    public void functional_collect_test() {
        List nums = [3, 1, 4, 1, 5, 9]
        println nums.collect { it * 2 } // no side effect, returns new collection
    }
    /*
    output:
    [6, 2, 8, 2, 10, 18]
     */

    @Test
    public void functional_collect_findall_test() {
        List nums = [3, 1, 4, 1, 5, 9]
        println nums
                .collect { it * 2 }
                .findAll { it % 3 == 0 } // no side effect, returns new collection
    }
    /*
    output:
    [6, 18]
     */

    @Test
    public void functional_map_filter_reduce_test() {
        List nums = [3, 1, 4, 1, 5, 9]
        println nums
                .collect { it * 2 }      // map
                .findAll { it % 3 == 0 } // filter
                .sum()                  // reduce
    }
    /*
    output:
    24
     */

    @Test
    public void spread_dot_test() {
        List strings = 'this is a list of strings'.split()
        println strings.collect { it.size() }
        println strings*.size() // spread dot
    }
    /*
    output:
    [4, 2, 1, 4, 2, 7]
    [4, 2, 1, 4, 2, 7]
     */

    @Test
    public void map_collect_join_test() {
        def map = [k1: 'v1', k2: 'v2', k3: 'v3']
        println map.collect { k, v -> "$k=$v" }
                .join('&')
    }
    /*
    output:
    k1=v1&k2=v2&k3=v3
     */

}
