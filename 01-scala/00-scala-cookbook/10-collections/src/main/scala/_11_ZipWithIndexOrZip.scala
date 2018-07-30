/*
Using zipWithIndex or zip to Create Loop Counters

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s12.html
 */
object _11_ZipWithIndexOrZip {
  def main(args: Array[String]): Unit = {
    val days = Array("Sunday", "Monday", "Tuesday")
    days.zipWithIndex.foreach{
      case (day, count) => println(s"$count is $day")
    }
  }
}
/*
0 is Sunday
1 is Monday
2 is Tuesday
 */
