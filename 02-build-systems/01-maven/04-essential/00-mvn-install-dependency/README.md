# mvn install dependency

## Parent project

> add Customer model

```
public class Customer {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
public static void main( String[] args )
{
    Customer customer = new Customer();
    customer.setName("Tom");
    System.out.println(customer.getName());
}
```

> Build

mvn clean package

> Test

java -jar target/child-1.0-SNAPSHOT.jar

output:
Tom
