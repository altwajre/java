# Configuration and Properties

https://www.safaribooksonline.com/library/view/essentials-of-spring/9781787283893/video2_1.html

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

## Debug to view matched configurations

application.properties

```
debug=true
```

## @ConfigurationProperties

SimpleProperties.java

```
@ConfigurationProperties(prefix = "simple")
@Validated
public class SimpleProperties {
...
}
```

application.properties

```
simple.name=Simple Properties
simple.enabled=true
simple.description=${simple.name} is a collection of  properties.
```

## spring.profiles

application.properties

```
spring.profiles.active=development
```

application-development.yml

```
my:
  property: Mastering Spring - Development

simple:
  name: Simple Development Properties
  enabled: true
  description: ${simple.name} is a collection of  properties.
```

## Test

http://localhost:8080/