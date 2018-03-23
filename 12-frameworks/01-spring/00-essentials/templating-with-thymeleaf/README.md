# Templating with Thymeleaf

https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html

https://www.safaribooksonline.com/library/view/essentials-of-spring/9781787283893/video3_3.html
https://www.youtube.com/watch?v=Muij9ZVuEEE&list=PL-KSt1w_h7Kn15WNZ0NyI02mVLkQTeDyc

ppm.xml

```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
      <groupId>nz.net.ultraq.thymeleaf</groupId>
      <artifactId>thymeleaf-layout-dialect</artifactId>
      <version>2.2.1</version>
    </dependency>
    <dependency>
      <groupId>com.github.mxab.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-data-attribute</artifactId>
      <version>2.0.1</version>
    </dependency>
    <dependency>
      <groupId>org.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-java8time</artifactId>
      <version>3.0.0.RELEASE</version>
    </dependency>
```

SimpleController.java

```
@Controller
public class SimpleController {
  @RequestMapping("/")
  public String index() {
    return "index";
  }
  @ModelAttribute("simpleValue")
  public String simpleValue() {
    return "Hello!";
  }
  @ModelAttribute("today")
  public LocalDate localDate() {
    return LocalDate.now();
  }
}
```

resources/templates/index.html

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:data="http://www.thymeleaf.org/extras/data">
<head>
<body>
<div data:simple="${simpleValue}">
  <h2 th:inline="text">[[${today}]]</h2>
  <p>Today is: <span th:text="${today}">13 February 2011</span></p>
  <span th:text="${simpleValue}">override me</span>
</div>
</body>
</head>
</html>
```

- Test

http://localhost:8080/

## table

SimpleController.java

```
    @RequestMapping("/personList")
    public String content1() {
        return "personList";
    }
```

```
<section layout:fragment="content">
    <div class="personList">
        <h2>All People</h2>
        <table border="1">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="person : ${allPersons}">
                <td th:text="${person.firstName}"></td>
                <td th:text="${person.lastName}"></td>
            </tr>
            </tbody>
        </table>
    </div>
</section>
```

- Test

http://localhost:8080/personList

