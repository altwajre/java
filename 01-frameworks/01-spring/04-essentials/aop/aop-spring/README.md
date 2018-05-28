# aop

https://www.youtube.com/watch?v=QdyLsX0nG30&list=PLE37064DE302862F8
http://www.springboottutorial.com/spring-boot-and-aop-with-spring-boot-starter-aop

Person.java - need @Service

```
@Service
@Data
public class Person {
  private String name;
  private int age;
}
```


LoggingAspect.java - need @Aspect and @Configuration

```
@Aspect
@Configuration
public class LoggingAspect {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
//  @Before("allPersonMethods()")
  @Before("execution(* com.company.app.models.*.*(..))")
  public void loggingAdvice(JoinPoint joinPoint) {
    logger.info("### Allowed execution for {}", joinPoint);
  }
  @Pointcut("within(com.company.app.models.*)")
  public void allPersonMethods() {
  }
}
```

AopTest.java - need @Autowired

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {
  @Autowired
  private Person tom;
  @Test
  public void testPerson() {
    tom.setName("Tom");
    System.out.println(tom.getName());
  }
}
```

output:

```
### Allowed execution for execution(void com.company.app.models.Person.setName(String))
### Allowed execution for execution(String com.company.app.models.Person.getName())
```
