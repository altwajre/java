# Building a RESTful Web Service

https://spring.io/guides/gs/rest-service/

## Run

mvn clean package && java -jar target/swagger-1.0-SNAPSHOT.jar

curl http://localhost:8080/greeting
curl http://localhost:8080/greeting?name=User

## Docker

docker build -t spring-service .
docker run --name spring-service -d -p 8080:8080 spring-service
                                   host port : container port

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
        .apis(RequestHandlerSelectors.basePackage("com.company.app"))
        .paths(PathSelectors.any())
        .build();
  }
}
```
