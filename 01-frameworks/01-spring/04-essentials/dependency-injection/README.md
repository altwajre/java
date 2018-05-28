# dependency injection

https://stormpath.com/blog/spring-boot-dependency-injection

```
public interface GreetingService {
  String greet();
}
```

- implementation class need @Service for dependency injection to work

```
@Service
public class EnglishGreetingService implements GreetingService {
  @Override
  public String greet() {
    return "EnglishGreetingService: Hello World!";
  }
}
```

- @Autowired dependency injection
 
```
@RestController
public class HomeController {
  @Autowired
  GreetingService greetingService;

  @RequestMapping("/")
  public String home() {
    return greetingService.greet();
  }
}
```

- need config when there are multiple implementations of an interface

```
@Configuration
public class GreetingServiceConfig {

  @Bean
  @Primary
  @ConditionalOnProperty(name = "language.name", havingValue = "english", matchIfMissing = true)
  public GreetingService englishGreetingService() {
    return new EnglishGreetingService();
  }

  @Bean
  @ConditionalOnProperty(name = "language.name", havingValue = "french")
  public GreetingService frenchGreetingService() {
    return new FrenchGreetingService();
  }
}
``` 
