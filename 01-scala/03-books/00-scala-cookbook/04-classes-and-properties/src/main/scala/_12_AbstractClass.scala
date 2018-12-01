/*
When to Use an Abstract Class

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s13.html

main reasons to use an abstract class
- You want to create a base class that requires constructor arguments
- The code will be called from Java code
 */
object _12_AbstractClass {
  // base class
  abstract class Animal(name: String) {
    override def toString: String = s"base class: $name"
  }
  class Dog(name: String) extends Animal(name) {
    println("subclass: Dog constructor called")
  }
  def main(args: Array[String]): Unit = {
    val dog = new Dog("Rocky")
    println(dog)
  }
}
/*
subclass: Dog constructor called
base class: Rocky
 */
