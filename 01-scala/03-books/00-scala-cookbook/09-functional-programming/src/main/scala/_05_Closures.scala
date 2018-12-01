/*
Using Closures

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s06.html

pass a function sd a variable, and the function refers to one or more fields were in the same scope as the function was declared.
 */
object _05_Closures {
  class Foo {
    /*
     a method that takes a function and a string,
     and passes the string into the function,
     and then executes the function
      */
    def exec(f:(String) => Unit, name: String): Unit = {
      f(name)
    }
  }
  def main(args: Array[String]): Unit = {
    var hello = "Hello"
    def sayHello(name: String): Unit = {
      println(s"$hello, $name")
    }

    // execute sayHello from the exec method foo
    val foo = new Foo
    foo.exec(sayHello, "Tom")

    println("# Change values")
    // change the local variable 'hello;, then execute sayHello from the exec method of foo
    hello = "Hola"
    foo.exec(sayHello, "Dick")
  }
}
/*
Hello, Tom
# Change values
Hola, Dick
 */
