package com.company.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    FeatureLoginTest.class,
    FeatureLogoutTest.class
})
public class AggregatingTestsInSuitesTest {
  // the class remains empty,
  // used only as a holder for the above annotations
}
