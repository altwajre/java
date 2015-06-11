# java
java

## Tutorials

> https://docs.oracle.com/javase/tutorial/

> https://docs.oracle.com/javase/tutorial/essential/index.html

## Design Pattern

> http://www.tutorialspoint.com/design_pattern/

> https://github.com/iluwatar/java-design-patterns


## Intellij

> https://www.jetbrains.com/idea/help/keyboard-shortcuts-you-cannot-miss.html

```
Shortcut	Description
Alt Enter	Show the list of available intention actions.
```

## Windows

note: download and install `Windows x64` packages

### Install Java JDK

download JDK `Windows x64`

> http://www.oracle.com/technetwork/java/javase/downloads/index.html

### Install IDE

(optional) install NetBeans IDE

> https://netbeans.org/features/index.html

install Intellij Community Edition

> https://www.jetbrains.com/idea/

### Install Maven

download `apache-maven-3.3.3-bin.zip`

> https://maven.apache.org/download.cgi

```
Windows
Unzip the distribution archive, i.e. apache-maven-3.3.3-bin.zip to the directory you wish to install Maven 3.3.3. These instructions assume you chose C:\Program Files\Apache Software Foundation. The subdirectory apache-maven-3.3.3 will be created from the archive.
Add the unpacked distribution's bin directory to your user PATH environment variable by opening up the system properties (WinKey + Pause), selecting the "Advanced" tab, and the "Environment Variables" button, then adding or selecting the PATH variable in the user variables with the value C:\Program Files\Apache Software Foundation\apache-maven-3.3.3\bin.
Optional: In the same dialog, add the MAVEN_OPTS environment variable in the user variables to specify JVM properties, e.g. the value -Xms256m -Xmx512m. This environment variable can be used to supply extra options to Maven.
In the same dialog, make sure that JAVA_HOME exists in your user variables or in the system variables and it is set to the location of your JDK, e.g. C:\Program Files\Java\jdk1.7.0_51.
Open a new command prompt (Winkey + R then type cmd) and run mvn --version to verify that it is correctly installed.
```
