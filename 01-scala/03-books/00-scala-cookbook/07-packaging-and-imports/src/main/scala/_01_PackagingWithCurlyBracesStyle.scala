/*
Packaging with the Curly Braces Style Notation

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch07s02.html
 */
// a package containing a class named Foo
package orderentry {
  class Foo {
    override def toString: String = "I am orderentry.Foo"
  }
}

// one package nested inside the other
package customers {
  class Foo {
    override def toString: String = "I am customers.Foo"
  }

  package database {
    // this Foo is different than customers.Foo or orderentry.Foo
    class Foo {
      override def toString: String = "I am customers.database.Foo"
    }
  }
}

object PackagingWithCurlyBracesStyle {
  def main(args: Array[String]): Unit = {
    println(new orderentry.Foo)
    println(new customers.Foo)
    println(new customers.database.Foo)
  }
}
/*
I am orderentry.Foo
I am customers.Foo
I am customers.database.Foo
 */
