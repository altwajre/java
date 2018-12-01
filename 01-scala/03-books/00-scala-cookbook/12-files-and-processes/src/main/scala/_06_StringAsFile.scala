import scala.io.Source

/*
Pretending that a String Is a File

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s07.html
 */
object _06_StringAsFile {
  def printlines(source: Source): Unit = {
    for(line <- source.getLines) {
      println(line)
    }
  }
  def main(args: Array[String]): Unit = {
    val source = Source.fromString("foo\nbar\n")
    printlines(source)
  }
}
/*
foo
bar
 */
