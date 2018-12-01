# Multi-Module sbt project

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch18s07.html

https://www.safaribooksonline.com/videos/scala-beginner-programming/9781788295567/9781788295567-video1_5

## Steps

> 1. root project

1. create the root sbt project
2. remove the root src folder

> 2. common project

1. create a common sub-project
2. add Customer.scala
```
package com.company.common
case class Customer(name: String)
```

> 3. runner project

1. create a runner sub-project that will use the common sub-project above

> 4. root project build.sbt

1. add sub projects - runner depends on common
```
lazy val common = project in file("common")
lazy val runner =
  (project in file("runner"))
  .dependsOn(common)
```

> 5. runner project uses common project class

1. use common project code in runner project

```
import com.company.common.Customer
object Runner {
  def main(args: Array[String]): Unit = {
    val tom = Customer("Tom")
    println(tom)
  }
}
```

## Test

Customer(Tom)
