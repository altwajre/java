# The Spring Web MVC

## spring mvc

http://docs.spring.io/autorepo/docs/spring/3.2.x/spring-framework-reference/html/mvc.html

## mvc project

- New, Spring Starter Project, name="spring-mvc", next, check `web`, click finish
- add a WebController.java
```
package com.example.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class WebController {
	private static Logger logger = LoggerFactory.getLogger(WebController.class);
	@RequestMapping("/")
	public String index(){
		logger.info("/ is called");
		return "index"; // index view
	}
	@RequestMapping("/about")
	public String about(){
		logger.info("/about is called");
		return "about"; // about view
	}
}
```
- add "index.jsp" and "about.jsp" under `src/main/webapp/WEB-INF/jsp`
> index
```
<title>Index</title>
</head>
<body>
<h1>Hello Mastering Cassandra</h1>
</body>
```
> about
```
<title>about</title>
</head>
<body>
<h1>About</h1>
</body>
```

- hook up the view in `application.properties`

```
spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp
```

## Run App

Right click the project icon, `Run As`, `Spring Boot App`
