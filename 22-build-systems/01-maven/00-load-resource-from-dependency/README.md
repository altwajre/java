# load resource from dependency

## Parent project - mvn install

```
@Data
public class Customer {
  private String name;
  private Integer age;
}

public class CustomerFactory {
  public Customer create(){
    ObjectMapper mapper = new ObjectMapper();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("data/customer.json");

    Customer customer = null;
    try {
      customer = mapper.readValue(inputStream, Customer.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return customer;
  }
}
```

> `mvn install` parent pom - put parent jar into local repository, so it is available for child project

mvn clean install

## Child project

After parent jar is installed, we can add parent dependency to child project

```
<dependency>
  <groupId>com.company.app</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

> `child` project can use Customer model that is in parent project

```
public class App {
  public static void main(String[] args) {
    Customer customer = new CustomerFactory().create();
    System.out.println(customer);
  }
}
```

> Build

mvn clean package

> Test

java -jar target/child-1.0-SNAPSHOT.jar

output:
Customer(name=Tom, age=28)
