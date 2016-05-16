# Setting Up Our Toolbox

## Eclipse IDE

https://spring.io/tools

## Spring Framework

https://spring.io/projects

http://projects.spring.io/spring-boot/

### Create sprint boot project - demo

- launch STS IDE
- File, New Spring Starter Project, next, check `Web` under Web, click finish
- expand `src/main/java`, right click `com.example`, click package, add `com.example.controller`
- right click `com.example.controller`, click class, name=`HelloSpring`
```
package com.example.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloSpring {

	@RequestMapping("/")
	String home(){
		return "Hello, Spring!";
	}
}
```
- right click on `demo` project, `Run As`, `Spring Boot App`
- go to http://localhost:8080/ - output: Hello, Spring!

#### import project

right click `Package Explorer`, click `Import...`, expand `Maven`, select `Existing Maven Projects`, browse project

## Tomcat
### Start Server

location: `/Library/Tomcat/`

Tomcat whan$ bin/startup.sh - if it doesn't work, shutdown and startup again

Tomcat whan$ bin/shutdown.sh

### Manager App

click `Manager App` button, and login with username: admin, password: admin
