package com.company.app.test;

import com.company.app.test.MyMathTest;
import com.company.app.test.TaxTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MyMathTest.class, TaxTest.class})
public class AllTests {
}
