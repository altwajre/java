# Java

## JVM

The Java Virtual Machine is a software virtual machine that runs compiled Java code. Because compiled Java code is
merely bytecode, the JVM is responsible for compiling that bytecode to machine code before running it. The JVM also
takes care of memory management so that application code doesn't have to.

https://www.youtube.com/watch?v=XjNwyXx2os8

## JDK

The Java Development Kit was and remains the piece of software Java developers use to create Java applications. It
contains a Java language compiler, a documentation generator, tools for working with native code, and (typically) the
Java source code for the platform to enable debugging platform classes.

## JRE

The Java Runtime Environment, is the piece of software end users download to run compiled Java applications. It includes
a JVM but does not contain any of the development tools bundled in the JDK. However, the JDK does contain a JRE.

https://www.youtube.com/watch?v=yLkrw34VMNo

## KB

### Error: source release 8 requires target release 1.8

Solution: add following plugin to maven pom.xml
```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.3</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

### ClassNotFoundException - solution: add maven-shade-plugin

```
Problem:
$ java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App
Exception in thread "main" java.lang.NoClassDefFoundError: org/json/JSONObject
	at com.company.test.App.main(App.java:22)
Caused by: java.lang.ClassNotFoundException: org.json.JSONObject
Solution: add following
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
```

## Tutorials

> https://docs.oracle.com/javase/tutorial/

> https://docs.oracle.com/javase/tutorial/essential/index.html

## Design Pattern

> http://www.tutorialspoint.com/design_pattern/

> https://github.com/iluwatar/java-design-patterns

> https://sourcemaking.com/design_patterns

## Tools

### Java VisualVM

#### Mac

> double click `jvisualvm` at `/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/bin` to open it

## Compiler

https://www.youtube.com/watch?v=8y0L9QT7U74

