/*
Replacing Patterns in Strings

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s08.html
 */
object _07_ReplacingPatterns {
  def main(args: Array[String]): Unit = {
    val address = "123 Main Street".replaceAll("[0-9]", "x")
    println(address)

    val regex = "[0-9]".r
    val newAddress = regex.replaceAllIn("123 Main Street", "x")
    println(newAddress)

    println("123".replaceFirst("[0-9]", "x"))

    val regex2 = "H".r
    println(regex2.replaceFirstIn("Hello world", "J"))

  }
}
/*
xxx Main Street
xxx Main Street
x23
Jello world
 */
