import scala.io.Source._

object _08_AssignBlockOrFunctionToField {
  class Foo {
    // set 'text' equal to the result of the block of code
    val block = {
      var lines = ""
      try {
        lines = fromFile("/etc/aaa").getLines.mkString
      }
      catch {
        case e: Exception => lines = "Error happened"
      }
      lines
    }

    val function = (x: Int) => x * x
  }

  def main(args: Array[String]): Unit = {
    val b = new Foo

    println("# assign block to field")
    println(b.block)

    println("# assign function to field")
    println(b.function(2))
  }
}
/*
# assign block to field
Error happened
# assign function to field
4
 */
