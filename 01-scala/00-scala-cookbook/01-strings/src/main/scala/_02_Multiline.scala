/*
Creating Multiline Strings

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s03.html
 */
object _02_Multiline {
  def main(args: Array[String]): Unit = {
    println("# pipe symbol |")
    val speech1 =
      """Four score and
        |seven years ago
      """.stripMargin
    println(speech1)

    println("# pipe symbol #")
    val speech2 =
      """Four score and
        #seven years ago
      """.stripMargin('#')
    println(speech2)

    println("# .stripMargin.replaceAll")
    val speech3 =
      """Four score and
        |seven years ago
      """.stripMargin.replaceAll("\n", " ")
    println(speech3)

    println("\n# string has single and double quotes")
    val speech4 =
      """Four "score" and
        |'seven' years ago
      """.stripMargin.replaceAll("\n", " ")
    println(speech4)

  }
}
/*
# pipe symbol |
Four score and
seven years ago

# pipe symbol #
Four score and
seven years ago

# .stripMargin.replaceAll
Four score and seven years ago

# string has single and double quotes
Four "score" and 'seven' years ago
 */
