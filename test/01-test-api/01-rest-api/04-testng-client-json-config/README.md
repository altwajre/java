# testng-client

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

> `export TEST_ENV=local; java -cp target/app-1.0-SNAPSHOT.jar com.company.app.TestRunner`

> note: `export TEST_ENV=local` <- set environment variable before running test

> `mvn test`

