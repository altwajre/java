import org.junit.Test;

import static org.junit.Assert.*;

class GroovyPersonTest {
    @Test
    void testUseGroovyToTestJava(){
        String expectedName = 'Harry'
        int expectedAge = 18
        Person person = new Person("Harry", 18)
        assertEquals(person.getName(), expectedName)
        assertEquals(person.getAge(), expectedAge)
    }
}
