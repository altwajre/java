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


