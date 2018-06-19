/*
Product ADTs

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/775b657c-70bf-40b4-b729-bec0c15c66ee.xhtml

In product algebraic data types, we cannot enumerate all the possible values.
There are usually too many to manually write them. We cannot provide a separate constructor for each separate value.

 */

// For product ADTs, we have one constructor for different values
sealed case class RGB(red: Int, green: Int, blue: Int)

object ProductADTs {
  def main(args: Array[String]) = {
    val magenta = RGB(255, 0, 255)
    println(s"Magenta is RGB is $magenta")
  }
}
/*
Magenta is RGB is RGB(255,0,255)
 */
