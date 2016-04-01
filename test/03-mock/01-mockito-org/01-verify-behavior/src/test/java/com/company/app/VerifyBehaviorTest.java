package com.company.app;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
http://mockito.org/

Once created, a mock will remember all interactions. Then you can selectively verify whatever interactions you are interested in.
 */
public class VerifyBehaviorTest {
    @Test
    public void verify_behavior(){
        // mock creation
        List mockedList = mock(List.class);

        // using mock object
        mockedList.add("one");
        mockedList.clear();

        // verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
