package value_object

/*
Alternative implementation

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/d292df55-65cc-4994-8ce5-99d010f8cfbc.xhtml

The value object design pattern can also be achieved with predefined tuple classes in Scala.
 */
object Tuple {
  def main(args: Array[String]) = {
    val date1 = (3, "March", 2016)
    val date2 = (4, "JULY", 2016)

    println(s"date1 == date2 ? ${date1 == date2}")

    val year1 = (31, "DECEMBER", 2015)
    val year2 = (31, "DECEMBER", 2015)
    println(s"year1 == year2 ? ${year1 == year2}")
  }
}
/*
date1 == date2 ? false
year1 == year2 ? true
 */
