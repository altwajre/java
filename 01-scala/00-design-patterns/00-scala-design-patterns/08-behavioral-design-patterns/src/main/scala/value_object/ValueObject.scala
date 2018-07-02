package value_object

/*
The value object design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/91d6f8d6-3b8a-468c-b51c-9c73b45d4cf2.xhtml

Value objects are small and simple immutable objects. Their equality is based not on identify, but on value equality.

case class does everything by creating default implementations for the hashCode, equals and toString.
 */
case class Date(
                 day: Int,
                 month: String,
                 year: Int
               )

object ValueObject {
  def main(args: Array[String]) = {
    val thirdOfMarch = Date(3, "MARCH", 2016)
    val fourthOfJuly = Date(4, "JULY", 2016)
    println(s"thirdOfMarch == fourthOfJuly ? ${thirdOfMarch == fourthOfJuly}")

    val newYear1 = Date(31, "DECEMBER", 2015)
    val newYear2 = Date(31, "DECEMBER", 2015)
    println(s"newYear1 == newYear2 ? ${newYear1 == newYear2}")
  }
}
/*
thirdOfMarch == fourthOfJuly ? false
newYear1 == newYear2 ? true
 */
