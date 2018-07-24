/*
Calling a Superclass Constructor

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s12.html

you can control the superclass constructor that's called by the primary constructor in a subclass
you can't control the superclass constructor that's called by an additional constructor in the subclass
 */
object _11_CallSuperclassConstructor {

  // (1) primary constructor
  class Animal(var name: String, var age: Int) {
    // (2) additional constructor
    def this (name: String) {
      this(name, 0)
    }

    override def toString: String = s"base class: $name is $age years old"
  }

  // calls the Animal one-arg constructor
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
base class: Rocky is 0 years old
 */
