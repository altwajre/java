import scala.util.Random

/*
Extractor Objects

https://docs.scala-lang.org/tour/extractor-objects.html

An extractor object is an object with an unapply method.
the unapply takes an object and tries to give back the arguments.
 */

case class Foo4()

object ExtractorObjects {
  def main(args: Array[String]) = {
    object CustomerID {
      def apply(name: String) = s"$name--${Random.nextLong}"
      def unapply(customerID: String): Option[String] = {
        val stringArray: Array[String] = customerID.split("--")
        if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
      }
    }

    val customerID = CustomerID("Sukyoung")
    println(customerID)

    println("## unapply()")
    // unapply takes object and give back arguments. It is used in pattern matching and partial functions.
    customerID match {
      case CustomerID(name) => println(name)
      case _ => println("Could not extract a CustomerID")
    }
    // It is equivalent to below
    println(CustomerID.unapply(customerID).get)
  }
}
/*
Sukyoung---4587444460637921562
## unapply()
Sukyoung
Sukyoung
 */
