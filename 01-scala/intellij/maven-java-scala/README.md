# Maven Java Scala

https://stackoverflow.com/questions/42354934/error-could-not-find-or-load-main-class-in-scala/43555061

pom.xml

```
  <repositories>
    <repository>
      <id>scala-tools.org</id>
      <name>Scala-Tools Maven2 Repository</name>
      <url>http://scala-tools.org/repo-releases</url>
    </repository>
  </repositories>
  <pluginRepositories>
    <pluginRepository>
      <id>scala-tools.org</id>
      <name>Scala-Tools Maven2 Repository</name>
      <url>http://scala-tools.org/repo-releases</url>
    </pluginRepository>
  </pluginRepositories>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <scala.version>2.11.4</scala.version>
  </properties>
  <dependencies>
    <dependency>
      <groupId>org.scala-lang</groupId>
      <artifactId>scala-library</artifactId>
      <version>${scala.version}</version>
    </dependency>
    <dependency>
      <groupId>org.specs</groupId>
      <artifactId>specs</artifactId>
      <version>1.2.5</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.scala-tools</groupId>
        <artifactId>maven-scala-plugin</artifactId>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
              <goal>testCompile</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <scalaVersion>${scala.version}</scalaVersion>
          <args>
            <arg>-target:jvm-1.8</arg>
          </args>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.scala-tools</groupId>
        <artifactId>maven-scala-plugin</artifactId>
        <configuration>
          <scalaVersion>${scala.version}</scalaVersion>
        </configuration>
      </plugin>
    </plugins>
  </reporting>
```

ScalaHello.scala

```
object ScalaHello {
  def main(args: Array[String]): Unit = {
    println("Scala says Hello, world!")
  }
}
```

## Use Scala from Java

http://lampwww.epfl.ch/~michelou/scala/using-scala-from-java.html

- Scala

Companion.scala

```
class com.company.app.Companion {
  def hello(): Unit = {
    println("Hello (class)")
  }
}

object com.company.app.Companion {
  def hallo(): Unit = {
    println("Hello (object)")
  }
}
```

- Java calls Scala

App.java

```
public class App {
  public static void main(String[] args) {
    callScalaMethods();
  }
  private static void callScalaMethods() {
    new com.company.app.Companion().hello(); // instance method
    com.company.app.Companion.hallo(); // static method
  }
}
```

## Use Java from Scala

- Java

Person.java

```
public class Person {
  private String name;
  private int age;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
}
```

- Scala calls Java

ScalaHello.scala

```
object ScalaHello {
  def main(args: Array[String]): Unit = {
    val tom = new Person()
    tom.setName("Tom")
    tom.setAge(28)
    println(s"Scala says Hello, ${tom.getName}!")
  }
}
```
