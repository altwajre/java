/*
Building a Pipeline of Commands

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s14.html

Use #| method to pipe commands
 */
import sys.process._

object _13_PipelineOfCommands {
  def main(args: Array[String]): Unit = {
    val numProcs = ("ps auxw" #| "wc -l").!!.trim
    println(s"#Procs = $numProcs")

    val javaProcs = ("ps auxw" #| "grep java").!!.trim
    println(s"#javaProcs:\n$javaProcs")

  }
}
/*
#Procs = 420
#javaProcs:
java1
java2
java3
 */
