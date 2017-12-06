# Hibernate Types

https://docs.jboss.org/hibernate/orm/4.2/manual/en-US/html/ch06.html

> Entity Types - have a database identity

Corresponds with a database row

> Value Types - no database identity

* Basic
* Composite Types
* Collection

```
@Entity
public class User {
  @Id
  private Long userId; // Basic Value Type
  private String firstName; // Basic Value Type
  private String lastName; // Basic Value Type
  private User referredBy; // Entity Type
  private List<String> aliases; // Collection Value Type
  private Address address; // Composite Value Type
}
```
