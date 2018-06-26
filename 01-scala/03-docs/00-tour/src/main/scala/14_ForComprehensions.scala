/*
For Comprehensions - for (enumerators) yield e

https://docs.scala-lang.org/tour/for-comprehensions.html

The for loop used with a yield statement actually creates a List.
 */

case class Foo5()

object ForComprehensions {
  def main(args: Array[String]) = {
    case class User(val name: String, val age: Int)

    val users = List(new User("Travis", 28),
      new User("Kelly", 33),
      new User("Jennifer", 44),
      new User("Dennis", 23))

    val twenties = for(user <- users if (user.age >= 20 && user.age < 30))
      yield user.name
    twenties.foreach(name => println(name))
  }
}
/*
Travis
Dennis
 */
