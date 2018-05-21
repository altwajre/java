# group tests

- Run tests

mvn clean test

- tests

```
public class MathTest {

  @Test (groups = {"pri1"})
  public void testAdd() {
    int result = Math.add(5, 3);
    System.out.println(result);
  }

  @Test (groups = {"smoke"})
  public void testSubtract() {
    int result = Math.subtract(8, 3);
    System.out.println(result);
  }

  @Test (groups = {"pri2"})
  public void pri2() {
    System.out.println("pri2");
  }

  @Test (groups = {"pending"})
  public void pendingTest() {
    System.out.println("pending");
  }
}
```

- pom.xml

run smoke and pri tests

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.21.0</version>
        <configuration>
          <suiteXmlFiles>
            <suiteXmlFile>test-smoke.xml</suiteXmlFile>
            <suiteXmlFile>test-pri1.xml</suiteXmlFile>
          </suiteXmlFiles>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

- test-smoke.xml

```
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="myTest">
  <test name="Regression">
    <groups>
      <run>
        <include name="smoke"/>
        <exclude name="pending"/>
      </run>
    </groups>
    <packages>
      <package name="com.company.app"/>
    </packages>
  </test>
</suite>
```