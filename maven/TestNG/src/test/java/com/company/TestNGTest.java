package com.company;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test ()
public class TestNGTest {
    @DataProvider
    public Object[][] data() {
        return new String[][]{
                new String[]{"data1"}, new String[]{"data2"}
        };
    }

    @Test(dataProvider = "data")
    public void test(String d) {
        System.out.println(d);
        Assert.assertEquals("First", "First");
    }
}