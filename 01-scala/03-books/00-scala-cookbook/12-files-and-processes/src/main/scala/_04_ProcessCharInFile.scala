import scala.io.Source

/*
How to Process Every Character in a Text File

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s05.html
 */
object _04_ProcessCharInFile {
  def main(args: Array[String]): Unit = {
    val NEWLINE = 10
    val source = Source.fromFile("fileopen.txt")
    for (char <- source) {
      if(char.toByte != NEWLINE)
        print(char.toUpper + ", ")
      else
        println("")
    }
    source.close
  }
}
/*
L, I, N, E, 1,
L, I, N, E, 2,
 */
