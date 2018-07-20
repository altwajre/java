/*
Testing String Equality

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s02.html
 */
object _01_Equality {
  def main(args: Array[String]): Unit = {
    compareStrings

    compareStringWithNull

    compareToUppers

    compareByUsingEqualsIgnoreCase
  }

  private def compareByUsingEqualsIgnoreCase = {
    println("# compareByUsingEqualsIgnoreCase")
    val s1 = "Hello"
    val s2 = "hello"

    println(s"s1.equalsIgnoreCase(s2): ${s1.equalsIgnoreCase(s2)}")
  }
/*
s1.equalsIgnoreCase(s2): true
 */

  private def compareToUppers = {
    println("# compareToUppers")
    val s1 = "Hello"
    val s2 = "hello"

    println(s"s1.toUpperCase == s2.toUpperCase: ${s1.toUpperCase == s2.toUpperCase}")
  }
/*
s1.toUpperCase == s2.toUpperCase: true
 */

  private def compareStringWithNull = {
    println("# compareStringWithNull")
    val s1 = "Hello"
    val s2: String = null

    println(s"s1 == null: ${s1 == s2}")
    println(s"null == s1: ${s2 == s1}")
  }
/*
s1 == null: false
null == s1: false
 */

  private def compareStrings = {
    println("# compareStrings")
    val s1 = "Hello"
    val s2 = "Hello"
    val s3 = "H" + "ello"

    println(s"s1 == s2: ${s1 == s2}")
    println(s"s1 == s3: ${s1 == s3}")
  }
/*
s1 == s2: true
s1 == s3: true
 */
}
