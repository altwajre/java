/*
Parsing a Number from a String

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch02s02.html
 */
object _01_Parsing {

  def main(args: Array[String]): Unit = {
    base10

    base2

    implicit class StringToInt(s: String) {
      def toInt(radix: Int) = Integer.parseInt(s, radix)
    }

    println("1".toInt(2))
  }

  private def base2 = {
    println(Integer.parseInt("1", 2))
    println(Integer.parseInt("10", 2))
    println(Integer.parseInt("100", 2))
  }
/*
1
2
4
 */

  private def base10 = {
    println("# base10")
    println("100".toInt)
    println("100".toDouble)
    println("100".toFloat)
    println("1".toLong)
    println("1".toShort)
    println("1".toByte)
  }
/*
# base10
100
100.0
100.0
1
1
1
 */
}
