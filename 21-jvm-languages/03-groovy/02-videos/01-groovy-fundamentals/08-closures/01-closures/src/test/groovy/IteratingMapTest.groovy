import org.junit.Test

class IteratingMapTest {
    @Test
    public void java_iterating_map_test() {
        Map m = [a: 1, b: 2, c: 3]
        for (String key : m.keySet()) {
            def val = m[key]
            println "map[$key] = $val"
        }
    }
    /*
    output:
    map[a] = 1
    map[b] = 2
    map[c] = 3
     */

    @Test
    public void each_map_with_mapentry_test() {
        Map m = [a: 1, b: 2, c: 3]
        m.each { e ->
            println "m[${e.key}] == ${e.value}"
        }
    }
    /*
    output:
    m[a] == 1
    m[b] == 2
    m[c] == 3
     */

    @Test
    public void each_map_with_keyvalue_test(){
        Map m = [a: 1, b: 2, c: 3]
        m.each { k, v ->
            println "m[$k] == $v"
        }
    }
    /*
    output:
    m[a] == 1
    m[b] == 2
    m[c] == 3
     */

}
