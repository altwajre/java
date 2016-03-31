package com.company.app;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {
    StudentService service = new StudentService();
    StudentServiceSpy spy = new StudentServiceSpy();
    @Test
    public void enrolls_students() throws Exception{
        Student bob = new Student("001", "Robert Anthony");
        Student roy = new Student("002", "Roy Noon");
        // set spy
        service.setSpy(spy);

        // invoke method
        service.enrollToCourse("english", bob);
        service.enrollToCourse("history", roy);

        // assert that the method was invoked twice
        assertEquals(2, spy.invocation("enrollToCourse"));

        // get the method arguments for the first call
        List<Object> methodArguments = spy.arguments("enrollToCourse", 1).getParams();

        // get the method arguments for the second call
        List<Object> methodArguments2 = spy.arguments("enrollToCourse", 2).getParams();

        // verify, Bob enrolled to english first
        assertEquals("english", methodArguments.get(0));
        assertEquals(bob, methodArguments.get(1));

        // verify Roy enrolled to history
        assertEquals("history", methodArguments2.get(0));
        assertEquals(roy, methodArguments2.get(1));
    }
}