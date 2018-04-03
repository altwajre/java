# thymeleaf simple

pom.xml

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

resources/templates/index.html

```
<!DOCTYPE html>
<html lang="en">
<head>
<body>
<p>Hello World!</p>
</body>
</head>
</html>
```

controllers.IndexController.java

```
@Controller
public class IndexController {
  @RequestMapping("/")
  public String index() {
    return "index"; // return template index.html
  }
}
```

## Test

http://localhost:8080/
