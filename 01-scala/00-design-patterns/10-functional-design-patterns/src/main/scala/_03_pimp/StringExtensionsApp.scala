package _03_pimp.pimp

/*
The pimp my library design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1ba5418d-0103-44d6-aaf4-d2611234f1b0.xhtml

similar to extension methods in C#

 */

object StringExtensionsApp {

  // package object pimp{...} in the other file is the same as local implicit class below
//  implicit class StringExtensions(val s: String) extends AnyVal {
//    def isAllUpperCase: Boolean =
//      !(0 until s.length).exists {
//        case index =>
//          s.charAt(index).isLower
//      }
//  }

  def main(args: Array[String]): Unit = {
    println(s"Is 'test' all upper case: ${"Test".isAllUpperCase}")
    println(s"Is 'TEST' all upper case: ${"TEST".isAllUpperCase}")
  }
}
/*
Is 'test' all upper case: false
Is 'TEST' all upper case: true
 */
