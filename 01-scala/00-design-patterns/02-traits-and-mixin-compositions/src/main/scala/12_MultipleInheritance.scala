/*
The diamond problem

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/a335dd13-5e6b-4971-91f1-6b8a5ffc7291.xhtml

 */
trait A {
  def hello(): String = "Hello, I am trait A!"
  def pass(a: Int): String = s"Trait A said: 'You passed $a.'"
}

trait B extends A {
  override def hello(): String = "Hello from B"
}

trait C extends A {
  override def hello(): String = "Hello from C"
}

trait D extends B with C {

}

object Diamond extends D {
  def main(args: Array[String]): Unit = {
    println(hello())
  }
}
/*
Hello from C
 */
