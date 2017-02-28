# Test filtering

https://docs.gradle.org/current/userguide/java_plugin.html

```
gradle test --tests org.gradle.SomeTest.someSpecificFeature
gradle test --tests *SomeTest.someSpecificFeature
gradle test --tests *SomeSpecificTest
gradle test --tests *SomeSpecificTestSuite
gradle test --tests all.in.specific.package*
gradle test --tests *IntegTest
gradle test --tests *IntegTest*ui*
gradle test --tests "com.example.MyTestSuite"
gradle test --tests "com.example.ParameterizedTest"
gradle test --tests "*ParameterizedTest.foo*"
gradle test --tests "*ParameterizedTest.*[2]"
gradle someTestTask --tests *UiTest someOtherTestTask --tests *WebTest*ui
```
