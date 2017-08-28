# Builder vs Constructor

https://projectlombok.org/features/Builder.html
https://projectlombok.org/features/Constructor.html

> use @Builder and @NoArgsConstructor is not allowed

```
@Data
@Builder
@NoArgsConstructor // @NoArgsConstructor is not allowed when @Builder is already used
class Customer {
  private String name;
  private int age;
}
```

Error:

```
Error:(9, 1) java: constructor Customer in class com.company.app.Customer cannot be applied to given types;
  required: no arguments
  found: java.lang.String,int
  reason: actual and formal argument lists differ in length
```
