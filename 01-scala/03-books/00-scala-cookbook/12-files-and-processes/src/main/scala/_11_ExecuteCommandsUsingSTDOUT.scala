import scala.sys.process.Process

/*
Executing External Commands and Using STDOUT

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s12.html

- Use the !! method to execute the command and get its output
 */
object _11_ExecuteCommandsUsingSTDOUT {
  def main(args: Array[String]): Unit = {
    println("""# Process("ls").!!""")
    val result = Process("ls").!!
    println(s"result:\n$result")
  }
}
/*
# Process("ls").!!
result:
README.md
build.sbt
fileopen.txt
finance.csv
out
project
src
target
 */
