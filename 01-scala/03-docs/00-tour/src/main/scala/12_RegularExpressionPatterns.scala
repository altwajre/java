import scala.util.matching.Regex

/*
Regular Expression Patterns

https://docs.scala-lang.org/tour/regular-expression-patterns.html

Regular expressions are strings which can be used to find patterns (or lack thereof) in data.
Any string can be converted to a regular expression using the .r method.
 */

case class Foo3()

object RegularExpressionPatterns {
  def main(args: Array[String]) = {
    println("# One regular expression searching")
    val numberPattern: Regex = "[0-9]".r
    numberPattern.findFirstMatchIn("awesomepassword") match {
      case Some(_) => println("Password OK")
      case None => println("Password must contain a number")
    }

    println("# groups of regular expressions searching")
    val keyValPattern: Regex = "([0-9a-zA-Z-#() ]+): ([0-9a-zA-Z-#() ]+)".r

    val input: String =
      """background-color: #A03300;
        |background-image: url(img/header100.png);
        |background-position: top center;
        |background-repeat: repeat-x;
        |background-size: 2160px 108px;
        |margin: 0;
        |height: 108px;
        |width: 100%;""".stripMargin

    for(patternMatch <- keyValPattern.findAllMatchIn(input)) {
      println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")
    }
  }
}
/*
# One regular expression searching
Password must contain a number
# groups of regular expressions searching
key: background-color value: #A03300
key: background-image value: url(img
key: background-position value: top center
key: background-repeat value: repeat-x
key: background-size value: 2160px 108px
key: margin value: 0
key: height value: 108px
key: width value: 100
 */
