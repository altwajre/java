/*
Defining Properties in an Abstract Base Class (or Trait)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s14.html
 */
object _13_AbstractFields {
  abstract class Pet(name: String) {
    val greeting: String
    var age: Int
    def sayHello {
      println(greeting)
    }

    override def toString: String = s"I say $greeting, and I'm $age"
  }
  class Dog(name: String) extends Pet (name) {
    val greeting: String = "Woof"
    var age: Int = 2
  }
  class Cat(name: String) extends Pet (name) {
    override val greeting: String = "Meow"
    override var age: Int = 5
  }

  def main(args: Array[String]): Unit = {
    println("# Dog")
    val dog = new Dog("Fido")
    dog.sayHello
    println(dog)

    println("# Cat")
    val cat = new Cat("Morris")
    cat.sayHello
    println(cat)
    println("- after change age")
    cat.age = 10 // change age
    println(cat)
  }
}
/*
# Dog
Woof
I say Woof, and I'm 2
# Cat
Meow
I say Meow, and I'm 5
- after change age
I say Meow, and I'm 10
 */
