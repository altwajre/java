# Overview

> Composite and Collection Value Type Mappings

* Provide mapping metadata for a composite value type
* Map a collection of basic value types
* Map a collection of composite value types

> Composite Value Types

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
public class Address {
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String zipCode;
  private String addressType;
}
```

Both `User` and `Address` are in one table

`User` table

```
USER_ID BIGINT
FIRST_NAME VARCHAR(45)
LAST_NAME VARCHAR(45)
REFERRED_BY_USER_ID BIGINT
ADDRESS_LINE_1 VARCHAR(45)
ADDRESS_LINE_2 VARCHAR(45)
CITY VARCHAR(45)
STATE VARCHAR(45)
ZIP VARCHAR(45)
```

* Represent a group of values in a single Java type
* Embedded (JPA)/Composite Type (Hibernate)
* Do not confuse with entity
* Does not have an id or table
* Only persisted or queried in the context of its parent
* Data is embedded in the source object's table

> Collection Value Type

```
@Table(name = "BANK")
public class Bank {
  @Id
  private Long bankId;
  private String name;
  private List<String> contacts = new LinkedList<String>();
  private Address address = new Address();
  private boolean international;
}
```

collection value type `contacts` needs a table `BANK_CONTACT`

`BANK_CONTACT`

```
BANK_ID BIGINT(20)
NAME VARCHAR(100)
```
