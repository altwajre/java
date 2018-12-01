import scala.io.Source

/*
How to Open and Read a Text File

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s02.html
 */
object _01_ReadTextFile {
  def main(args: Array[String]): Unit = {
    val filename = "fileopen.txt"
    for( line <- Source.fromFile(filename).getLines()) {
      println(line)
    }

    println("# .toList")
    println(Source.fromFile(filename).getLines.toList)

    println("# .toArray")
    println(Source.fromFile(filename).getLines.toArray.mkString(", "))

    println("# for (line <- bufferedSource.getLines)")
    val bufferedSource = Source.fromFile(filename)
    for (line <- bufferedSource.getLines) {
      println(line.toUpperCase)
    }
    bufferedSource.close
  }
}
/*
line1
line2
# .toList
List(line1, line2)
# .toArray
line1, line2
# for (line <- bufferedSource.getLines)
LINE1
LINE2
 */
