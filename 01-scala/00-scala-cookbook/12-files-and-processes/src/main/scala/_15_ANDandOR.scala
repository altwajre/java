/*
Using AND (&&) and OR (||) with Processes

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s16.html

Use Scala operators #&& and #||
 */
import sys.process._

object _15_ANDandOR {
  def main(args: Array[String]): Unit = {
    val result = ("ls temp" #&& "rm temp" #|| "echo 'temp' not found").!!.trim
    println(result)
  }
}
/*
ls: temp: No such file or directory
'temp' not found
 */
