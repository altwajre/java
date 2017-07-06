# A minimally viable wiki written with Vert.x

```
$ git clone https://github.com/vert-x3/vertx-maven-starter.git vertx-wiki
Cloning into 'vertx-wiki'...
remote: Counting objects: 66, done.
remote: Total 66 (delta 0), reused 0 (delta 0), pack-reused 66
Unpacking objects: 100% (66/66), done.

$ cd vertx-wiki/
$ rm -rf .git
$ mvn clean package exec:java

http://localhost:8080/
```

> pom.xml

1. it uses the Maven Shade Plugin to create a single Jar archive with all required dependencies, suffixed by -fat.jar,
also called "a fat jar"

2. it uses the Exec Maven Plugin to provide the exec:java goal that in turns starts the application through the Vert.x
io.vertx.core.Launcher class. This is actually equivalent to run using the vertx command-line tool that ships in the
Vert.x distribution

> AbstractVerticle base class

1. life-cycle `start` and `stop` methods to override
2. a protected field called `vertx` that references the Vert.x environment where the verticle is being deployed
3. an accessor to some configuration object that allows passing external configuration to a verticle

> Vert.x future objects and callbacks

Since all Vert.x APIs are callback-oriented before other abstractions like RxJava can be leveraged, 
this guide only uses callbacks in the first sections to ensure that the reader gets familiar with 
the core concepts in Vert.x. It is also arguably easier to start with callbacks to draw a line between 
the many sections of async code. Once it becomes evident in the sample code that callbacks do not always 
lead to easily readable code, we will introduce the RxJava support to show how the same async code 
can be better expressed by thinking in streams of processed events.

> verticle initialization phases

1. we need to establish a JDBC database connection, and also make sure that the database schema is in place
2. we need to start a HTTP server for the web application



