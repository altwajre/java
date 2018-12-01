/*
Creating Object Instances Without Using the new Keyword

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s09.html

Looks cleaner without new keyword to create a new instance of a class
- Create a companion object for class, and define an apply method in the companion object with desired constructor signature.
- Define your class as case class
 */
object _08_CreateObjectInstanceWithoutNewKeyword {
  class Person {
    var name: String = _
  }
  object Person {
    def apply(name: String): Person = {
      var p = new Person
      p.name = name
      p
    }
  }

  case class Customer(name: String)
  def main(args: Array[String]): Unit = {
    println("# companion object with apply method")
    val person = Person("Dick")
    println(person.name)

    println("# case class")
    val customer = Customer("Tom")
    println(customer)
  }
}
/*
# companion object with apply method
Dick
# case class
Customer(Tom)
 */
