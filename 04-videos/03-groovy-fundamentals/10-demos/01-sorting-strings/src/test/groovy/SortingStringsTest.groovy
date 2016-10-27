import org.junit.Test

class SortingStringsTest {
    @Test
    void java_storing_strings_test(){
        List strings = 'this is a list of strings'.split()
        println strings
        // Java natural sort
        Collections.sort(strings)
        println strings

        // Java sort by length
        Collections.sort(strings, new Comparator<String>() {
            @Override
            int compare(String s1, String s2) {
                s1.size() <=> s2.size()
            }
        })
        println strings

        assert strings*.size() == [1, 2, 2, 4, 4, 7]
    }
    /*
    output:
    [this, is, a, list, of, strings]
    [a, is, list, of, strings, this]
    [a, is, of, list, this, strings]
     */

    @Test
    void groovy_sort_test(){
        List strings = 'this is a list of strings'.split()
        // Groovy natural sort
        println strings.sort(false) // pass in false to create a new list instead of inplace sort

        // Groovy reverse length sort with a 2-param closure
        println strings.sort(false) { s1, s2 ->
            s2.size() <=> s1.size()
        }

        // Groovy length sort with a 1-param closure
        println strings.sort(false) { it.size() }

        // Groovy sort by length, then equal lengths alphabetically
        println strings.sort(false) { s1, s2 ->
            s1.size() <=> s2.size() ?: s1 <=> s2 // when the size compare is 0, sort strings
        }

    }
    /*
    output:
    [a, is, list, of, strings, this]
    [strings, this, list, is, of, a]
    [a, is, of, this, list, strings]
    [a, is, of, list, this, strings]
     */

}
