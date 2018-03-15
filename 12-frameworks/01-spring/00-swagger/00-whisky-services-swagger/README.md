# Spring REST Services

http://websystique.com/spring-boot/spring-boot-rest-api-example/
https://spring.io/guides/tutorials/bookmarks/

curl http://localhost:8080/whiskies
curl http://localhost:8080/whiskies/1
curl -X POST http://localhost:8080/whiskies -H 'content-type: application/json' -d '{"name": "Bowmore 18 Years", "origin": "Scotland", "fats": ["fat_11", "fat_12"]}'
curl -X PUT http://localhost:8080/whiskies/1 -H 'content-type: application/json' -d '{"name": "Bowmore 28", "origin": "Scotland 18", "fats": ["fat_18", "fat_28"]}'
curl -X DELETE http://localhost:8080/whiskies/2

## Swagger

http://localhost:8080/swagger-ui.html
http://localhost:8080/v2/api-docs

https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
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
