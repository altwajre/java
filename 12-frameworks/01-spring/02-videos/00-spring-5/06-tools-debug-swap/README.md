# Developer Tools, Debugging, and Hot Swapping

https://www.safaribooksonline.com/library/view/essentials-of-spring/9781787283893/video2_2.html

> add dependencies

```
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
```

security dependency is for docker remote

```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
```

## Automatic server restarts

### Intellij

- Build project automatically
`Preferences`, `Build, Execution, Deployment`, `Compiler`, check `Build project automatically`, click `Apply`
`Help`, `Find Action...`, type `registry`, check `complier.automake.allow.when.app.running`

### watch server auto restart

- change controller template

SimpleController.java - change return "index" to return "other"

```
public class SimpleController {
    public String index() {
        return "other";
    }
```

http://localhost:8080/

### exclude files auto server restart

application.properties

```
spring.devtools.restart.exclude=com/company/app/service/**
```

### include files auto server restart

application.properties

```
spring.devtools.restart.additional-paths=trigger
#spring.devtools.restart.trigger-file=trigger.txt
```

### disable auto server restart

application.properties

```
spring.devtools.restart.enabled=false
```

## Live reloads both locally and remotely

### docker

> add secret

application.properties

```
spring.devtools.remote.secret=secret
```

> launch app in docker

$ mvn clean package
$ docker-compose up

> Intellij

Run/Debug Configuration

- Under `Application`
- Name: `RemoteSpringApplication`
- Main class: `org.springframework.boot.devtools.RemoteSpringApplication`
- Program arguments: `http://localhost:8080`
- Click `Run`
