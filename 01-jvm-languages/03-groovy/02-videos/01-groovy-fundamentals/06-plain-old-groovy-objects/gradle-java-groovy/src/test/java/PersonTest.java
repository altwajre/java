import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void test1(){
        String expectedName = "Tom";
        int expectedAge = 10;
        Person person = new Person("Tom", 10);
        assertEquals(person.getName(), expectedName);
        assertEquals(person.getAge(), expectedAge);
    }
}