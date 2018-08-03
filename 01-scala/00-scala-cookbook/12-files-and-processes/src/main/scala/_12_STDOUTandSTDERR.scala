import sys.process._
import scala.sys.process.ProcessLogger

/*
Handling STDOUT and STDERR for External Commands

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s13.html
 */
object _12_STDOUTandSTDERR {
  def main(args: Array[String]): Unit = {
    val stdout = new StringBuilder
    val stderr = new StringBuilder
    val status = "ls -al FRED" ! ProcessLogger(stdout append _, stderr append _)
    println(status)
    println(s"stdout: $stdout")
    println(s"stderr: $stderr")
  }
}
/*
1
stdout:
stderr: ls: FRED: No such file or directory
 */
