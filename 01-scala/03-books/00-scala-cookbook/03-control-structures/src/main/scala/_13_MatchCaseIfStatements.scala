/*
Adding if Expressions (Guards) to Case Statements

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s14.html
 */
object _13_MatchCaseIfStatements {
  case class Stock(symbol: String, price: Int)
  case class Person(name: String)

  def matchIfStatement(x: Any): Unit = x match {
    // match a range of numbers
    case b if 10 to 19 contains b => println(s"10-19 range: $b")
    case c if 20 to 29 contains c => println(s"10-19 range: $c")

      // match different values of object
    case x if x == 1 => println("one, a lonely number")
    case x if (x == 2 || x == 3) => println(x)

    case s: Stock if (s.symbol == "XYZ" && s.price < 20) => println(s"buy: $s")
    case s: Stock if (s.symbol == "XYZ" && s.price > 50) => println(s"sell: $s")

    case _ => println("Unknown")
  }

  def speak(p: Person) = p match {
    case Person(name) if name == "Fred" => println("Yubba dubba doo")
    case Person(name) if name == "Bam Bam" => println("Bam bam!")
    case _ => println("Unknown person!")
  }

  def main(args: Array[String]): Unit = {
    matchIfStatement(18)
    matchIfStatement(28)

    matchIfStatement(1)
    matchIfStatement(3)

    matchIfStatement(Stock("XYZ", 18))
    matchIfStatement(Stock("XYZ", 68))

    speak(Person("Fred"))
    speak(Person("Bam Bam"))
  }
}
/*
10-19 range: 18
10-19 range: 28
one, a lonely number
3
buy: Stock(XYZ,18)
sell: Stock(XYZ,68)
Yubba dubba doo
Bam bam!
 */
