# reference other project class

`offer` project references `product` project `Project` class

> offer/pom.xml

includes product dependency

```
  <dependencies>
    <dependency>
      <groupId>com.company.app</groupId>
      <artifactId>product</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
```

> Run App

`offer` project, `App.main()`

> Result

```
Offer(id=0, name=phone_offer, product=Product(id=0, name=iPhone))
```
