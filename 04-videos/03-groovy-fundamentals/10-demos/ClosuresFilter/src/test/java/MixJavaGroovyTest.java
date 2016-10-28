import org.junit.Test;

// call groovy from java
public class MixJavaGroovyTest {
    @Test
    public void mix_java_groovy_test(){
        ProcessNumbers pn = new ProcessNumbers();
        System.out.println(pn.findPositives(3, -1, 4, -5, -9));
        System.out.println(pn.findPositives(-3, -1, -4, -5, -9));
    }
    /*
    output:
    [3, 4]
    []
     */
}
