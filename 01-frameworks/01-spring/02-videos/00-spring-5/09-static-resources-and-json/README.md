# Static Resources and Json

https://www.safaribooksonline.com/library/view/essentials-of-spring/9781787283893/video3_1.html

## Static Resources

pom.xml

```
<dependency>
  <groupId>org.webjars</groupId>
  <artifactId>webjars-locator</artifactId>
  <version>0.32</version>
</dependency>

<dependency>
  <groupId>org.webjars</groupId>
  <artifactId>bootstrap</artifactId>
  <version>3.3.7-1</version>
</dependency>
```

application.properties - specify static resources locations

```
spring.resources.static-locations=classpath:/html/,classpath:/static/,classpath:/resources/,classpath:/META-INF/resources,classpath:/public/
```

resources/html/index.html

```
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>Mastering Spring Development</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" /></head>
<body>
<div class="container">
    <div class="jumbotron"><h1>Mastering Spring!</h1></div>
    <div class="well">This is the index page.</div>
</div>

</body>
</html>
```

## Json

http://localhost:8080/orders

Order.java

```
public class Order {
  private Integer id;
  private Date orderDate;
  private Integer quantity;
  private Customer customer;
  private String productName;
  ...
}
```

- with Json-Serializer

output the Order POJO as json

```
{
id: 1,
orderDate: "2018-03-20T00:10:12.118+0000",
quantity: 1,
customer: {
id: 3,
firstName: "John",
lastName: "Doe"
},
productName: "Acme Portal"
}
```

### JsonObjectSerializer

OrderSerializer.java

```
@JsonComponent //automatically registers with Jackson in the ApplicationContext
public class SimpleSerializers {
  public static class OrderSerializer extends JsonObjectSerializer<Order> {
    @Override
    protected void serializeObject(Order value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeStringField("LastName", value.getCustomer().getLastName());
      jgen.writeStringField("FirstName", value.getCustomer().getFirstName());
      jgen.writeNumberField("amount", value.getQuantity());
      jgen.writeStringField("Product", value.getProductName());
    }
  }
}
```

output

```
{
LastName: "Doe",
FirstName: "John",
amount: 1,
Product: "Acme Portal"
}
```

## Error handling - custom 404 and 500 html

application.properties

```
spring.resources.static-locations=classpath:/html/
```

resources/html/error/404.html
resources/html/error/500.html

> Test

500 error

http://localhost:8080/myError

```
We're sorry, an error occurred.
```

404 error

http://localhost:8080/nothing

```
We're sorry, we aren't able to find anything there.
```

## Test

@WebMvcTest

- API 

MockMvc

```
@RunWith(SpringRunner.class)
@WebMvcTest(SimpleController.class)
public class SimpleControllerTest {
  @Autowired
  private MockMvc mvc;
  @Test
  public void getOrder() throws Exception {
    this.mvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.LastName", is("Doe")))
        .andExpect(jsonPath("$.FirstName", is("John")));
  }
```

- Selenium

pom.xml

```
<dependency>
  <groupId>net.sourceforge.htmlunit</groupId>
  <artifactId>htmlunit</artifactId>
  <scope>test</scope>
</dependency>
```

WebClient

```
  @Autowired
  private WebClient webClient;
  @Test
  public void testIndex() throws Exception {
    HtmlPage page = this.webClient.getPage("/index.html");
    assertThat(page.getBody().getTextContent()).isEqualTo("\n" +
        "\n" +
        "    Mastering Spring!\n" +
        "    This is the index page.\n" +
        "\n" +
        "\n" +
        "");
  }
}
```
