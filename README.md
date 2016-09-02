# java
java

## brew

http://brew.sh/

> `brew update`, `brew doctor`, `brew upgrade`

If we have following error when run `brew update`
```
LMDV-WHAN:01-protocol-buffers whan$ brew update
Error: /usr/local must be writable!
```

Run `sudo chown -R $USER:admin /usr/local` as below
```
LMDV-WHAN:01-protocol-buffers whan$ sudo chown -R $USER:admin /usr/local
```

## jenkins

### localhost
http://localhost:8080/

> start jenkins

sudo launchctl load /Library/LaunchDaemons/org.jenkins-ci.plist

> stop jenkins

sudo launchctl unload /Library/LaunchDaemons/org.jenkins-ci.plist

## Compiler

https://www.youtube.com/watch?v=8y0L9QT7U74

# Install Java

## Windows

note: download and install `Windows x64` packages

### Install Java JDK

download JDK `Windows x64`

> http://www.oracle.com/technetwork/java/javase/downloads/index.html

### JDK path

> `C:\Program Files\Java\jdk1.8.0_102`

## MAC

### Path

Edit Path Environment

- `$ atom ~/.bash_profile`
- `$ touch ~/.bash_profile; open ~/.bash_profile`
- `export ORGANIZATION="Tony's Pizza"` - set environment variable

### brew install java

> brew tap caskroom/cask

> brew install brew-cask

> brew cask install java

### install NetBeans IDE that comes with java

> https://netbeans.org/features/index.html

### Install IDE

#### Intellij

install Intellij Community Edition

> https://www.jetbrains.com/idea/

setup SDK

> path is `/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home`

Open Java Project

> Open pom.xml

References

> https://www.jetbrains.com/idea/help/keyboard-shortcuts-you-cannot-miss.html

```
Shortcut	Description
Alt Enter	Show the list of available intention actions.
```

#### Eclipse

Maven project

https://www.youtube.com/watch?v=uv9tXFrTLtI

### Install Maven

> `export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home`

> `brew install maven`

Or

download `apache-maven-3.3.3-bin.zip`

> https://maven.apache.org/download.cgi

```
Windows
Unzip the distribution archive, i.e. apache-maven-3.3.3-bin.zip to the directory you wish to install Maven 3.3.3. These
instructions assume you chose C:\Program Files\Apache Software Foundation. The subdirectory apache-maven-3.3.3 will be
created from the archive.
Add the unpacked distribution's bin directory to your user PATH environment variable by opening up the system properties
(WinKey + Pause), selecting the "Advanced" tab, and the "Environment Variables" button, then adding or selecting the
PATH variable in the user variables with the value C:\Program Files\Apache Software Foundation\apache-maven-3.3.3\bin.
Optional: In the same dialog, add the MAVEN_OPTS environment variable in the user variables to specify JVM properties,
e.g. the value -Xms256m -Xmx512m. This environment variable can be used to supply extra options to Maven.
In the same dialog, make sure that JAVA_HOME exists in your user variables or in the system variables and it is set to
the location of your JDK, e.g. C:\Program Files\Java\jdk1.7.0_51.
Open a new command prompt (Winkey + R then type cmd) and run mvn --version to verify that it is correctly installed.
```

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
