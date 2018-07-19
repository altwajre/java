case class Customer(name: String, age: Int)

object App {
  // type needs to be defined in object
  type Person = (String, Int)

  def main(args: Array[String]): Unit = {
    println("# type")
    val person = new Person("Tom", 18)
    println(person)

    println("# case class")
    val customer = Customer("Dick", 28)
    println(customer)
  }
}
/*
# type
(Tom,18)
# case class
Customer(Dick,28)
 */
