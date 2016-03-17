# slf4j-api, slf4j-log4j12, log to file

# Steps

## Maven dependencies

Add following to pom.xml
```
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.12</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.12</version>
    </dependency>
```

## Resource

Add src/main/resources/log4j.properties
```
log4j.rootLogger=DEBUG, STDOUT, file

            log4j.appender.STDOUT=org.apache.log4j.ConsoleAppender
            log4j.appender.STDOUT.layout=org.apache.log4j.PatternLayout
            log4j.appender.STDOUT.layout.ConversionPattern=%5p [%t] (%F:%L) - %m%n

            log4j.appender.file=org.apache.log4j.RollingFileAppender
            log4j.appender.file.File=mylogs.log
            log4j.appender.file.layout=org.apache.log4j.PatternLayout
            log4j.appender.file.layout.ConversionPattern=%d{dd-MM-yyyy HH:mm:ss} %-5p %c{1}:%L - %m%n
```

# Intellij

## Run Application

> Run, Run 'App'

## Output

```
 INFO [main] (App.java:11) - Hello world
```

## Log file

/mylogs.log has following

```
16-03-2016 21:46:31 INFO  App:11 - Hello world
```
