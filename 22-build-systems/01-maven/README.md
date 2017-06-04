# maven

https://maven.apache.org/download.cgi

## brew install

https://wilsonmar.github.io/maven-on-macos/

```
brew update
brew install maven
brew upgrade maven

mvn -version
which mvn
```

## IntelliJ 

open pom.xml will auto setup maven java project

## Predefined maven properties

https://github.com/cko/predefined_maven_properties/blob/master/README.md

## Important Commands

> `$ mvn site` - create a maven site for the project

> `$ mvn help:effective-pom` - inheritance all things from super pom

> `$ mvn archetype:generate` - generating projects

> `$ mvn eclipse:eclipse` - convert project to eclipse and get dependencies into eclipse

> `$ mvn archetype:generate -Dfilter=dropwizard` - generate dropwizard project

```
Choose archetype:
1: remote -> br.com.ingenieux:elasticbeanstalk-docker-dropwizard-webapp-archetype (A Maven Archetype for Publishing Dropwizard-based Services on AWS' Elastic
        Beanstalk Service)
2: remote -> io.paradoxical:dropwizard-api (A simple dropwizard (0.9.1) api template with swagger enabled, bundled as a docker container)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 2
Choose io.paradoxical:dropwizard-api version:
1: 0.9.2
2: 1.0
Choose a number: 2:
```

## Local repository

> `/Users/whan/.m2/repository`
