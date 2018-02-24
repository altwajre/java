# rest assured

## intellij

App.main()

## junit5

https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-running-unit-tests-with-maven/

need following for `mvn clean test` to work

```
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.19.1</version>
    <dependencies>
      <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-surefire-provider</artifactId>
        <version>1.0.1</version>
      </dependency>
      <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.0.3</version>
      </dependency>
    </dependencies>
  </plugin>
```

## Response Time

https://www.joecolantonio.com/2015/12/28/rest-assured-how-to-check-response-times/

/tests/WhiskyServicesResponseTimeTest.java

## Swagger

http://localhost:8080/swagger-ui.html

http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

- pom.xml

```
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.7.0</version>
</dependency>

<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.7.0</version>
</dependency>
```

- SwaggerConfig.java

```
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.company.app.services"))
        .paths(PathSelectors.any())
        .build();
  }
}
```

## Spring config port

- application.properties

```
server.port = 8081
```
