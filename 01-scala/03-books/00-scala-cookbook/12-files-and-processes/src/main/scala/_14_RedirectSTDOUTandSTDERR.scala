/*
Redirecting the STDOUT and STDIN of External

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s15.html

Use #> to redirect STDOUT, and #< to redirect STDIN
 */
import java.io.File
import java.net.URL

import sys.process._

object _14_RedirectSTDOUTandSTDERR {
  def main(args: Array[String]): Unit = {
    val theDir = new File("./out")
    if(!theDir.exists()) {
      println("create the dir")
      theDir.mkdir
    }
    else
      println("out dir exists")

    ("ls -al" #> new File("./out/files.txt")).!
    new URL("http://www.google.com") #> new File("./out/google.html") !
  }
}
/*
out dir exists
 */
