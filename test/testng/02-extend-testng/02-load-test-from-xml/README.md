# load-test-from-xml

http://testng.org/doc/javadocs/org/testng/TestNG.html

```
     TestNG tng = new TestNG();
     List suites = Lists.newArrayList();
     suites.add("c:/tests/testng1.xml");
     suites.add("c:/tests/testng2.xml");
     tng.setTestSuites(suites);
     tng.run();
```

## Create package

> `mvn package`

## Run

> In intellij, Run, Run 'App'

> `mvn test`
