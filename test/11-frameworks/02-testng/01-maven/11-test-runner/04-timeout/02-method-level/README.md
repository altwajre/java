# suite level

## test suite xml - test-all.xml

```
<suite name="test-all" verbose="1">
    <test name="Regression" group-by-instances="true">
        <packages>
            <package name="com.company.app.test" />
        </packages>
    </test>
</suite>
```

## Compile

> `mvn clean package`

## Run

> `$ java -jar target/app-1.0-SNAPSHOT.jar`

output:

```
[TestNG] Running:
  /Users/whan/Desktop/github/java-repo/7-2/java/test/22-testng/11-test-runner/01-run-all-tests/test-all.xml


===============================================
test-all
Total tests run: 2, Failures: 1, Skips: 0
===============================================
```

test result:

```
open ./test-output/index.html
```
