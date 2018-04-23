# maven scala project

> pom.xml

scala version should be 2.11.4

```
  <properties>
    <scala.version>2.11.4</scala.version>
  </properties>
```

> Hello.scala <- add this


```
object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}
```

> Remove all tests

## Test

Right click Hello.main(), select `Run`
