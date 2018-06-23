package adts
/*
Sum ADTs

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/732792f3-c611-4426-b46b-eb35d8d7f266.xhtml

Sum algebraic data types are the ones in which we can simply enumerate all the possible values of a type
and provide a separate constructor for each value.
 */
sealed abstract trait Month
case object January extends Month
case object February extends Month
case object March extends Month
case object April extends Month
case object May extends Month
case object June extends Month
case object July extends Month
case object August extends Month
case object September extends Month
case object October extends Month
case object November extends Month
case object December extends Month

object Month {
  def toInt(month: Month): Int =
    month match { // pattern matching
      case January => 1
      case February => 2
      case March => 3
      case April => 4
      case May => 5
      case June => 6
      case July => 7
      case August => 8
      case September => 9
      case October => 10
      case November => 11
      case December => 12
      case _ => 0
    }
}

object SumADTs {
  def main(args: Array[String]) = {
    val month: Month = February
    println(s"The current month is: $month and it's number ${Month.toInt(month)}")
  }
}
/*
The current month is: February and it's number 2
 */
