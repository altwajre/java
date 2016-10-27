import org.junit.Test

class MapsTest {

    @Test
    public void maps_test(){
        def map = [a:1, b:2, 'c':2]
        map.put('d', 3)
        map['e'] = 1
        map.f = 3
        println map
    }
    /*
    output:
    [a:1, b:2, c:2, d:3, e:1, f:3]
     */

    @Test
    public void linkedlist_test(){
        def nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] as LinkedList
        println nums
        println nums.class.name
    }
    /*
    output:
    [3, 1, 4, 1, 5, 9, 2, 6, 5]
    java.util.LinkedList
     */

    @Test
    public void sets_test(){
        def nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] as Set
        println nums
        println nums.class.name
    }
    /*
    output:
    [3, 1, 4, 5, 9, 2, 6]
    java.util.LinkedHashSet
     */

    @Test
    public void sortedset_test(){
        def nums = [3, 1, 4, 1, 5, 9, 2, 6, 5] as SortedSet
        println nums
        println nums.class.name
    }
    /*
    output:
    [1, 2, 3, 4, 5, 6, 9]
    java.util.TreeSet
     */

}
