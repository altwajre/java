/*
Extracting Parts of a String That Match Patterns

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s09.html
 */
object _08_ExtractingPartsOfString {
  def main(args: Array[String]): Unit = {
    val pattern = "([0-9]+) ([A-Za-z]+)".r
    val pattern(count, fruit) = "100 Bananas"
    println(s"count: $count, fruit: $fruit")
  }

}
/*
count: 100, fruit: Bananas
 */
