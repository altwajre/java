/*
Executing External Commands

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s11.html

- Use the ! method to execute the command and get its exit status
 */
import sys.process._

object _10_ExecuteCommands {
  def main(args: Array[String]): Unit = {
    println("""# "ls -al".!""")
    val exitCode = "ls -al".!
    println(s"- exitCode=$exitCode")

    println("""# Process("ls").!""")
    val exitCode2 = Process("ls").!
    println(s"- exitCode2=$exitCode2")
  }
}
/*
# "ls -al".!
total 32
drwxr-xr-x  11 whan  386085923  352 Aug  2 16:36 .
drwxr-xr-x  14 whan  386085923  448 Aug  1 22:40 ..
drwxr-xr-x  16 whan  386085923  512 Aug  2 21:49 .idea
-rw-r--r--   1 whan  386085923  109 Aug  1 22:40 README.md
-rw-r--r--   1 whan  386085923   73 Aug  1 22:40 build.sbt
-rw-r--r--   1 whan  386085923   11 Aug  2 15:25 fileopen.txt
-rw-r--r--   1 whan  386085923  108 Aug  2 16:36 finance.csv
drwxr-xr-x   6 whan  386085923  192 Aug  2 17:15 out
drwxr-xr-x   4 whan  386085923  128 Aug  1 22:40 project
drwxr-xr-x   4 whan  386085923  128 Aug  1 22:40 src
drwxr-xr-x   5 whan  386085923  160 Aug  1 22:40 target
- exitCode=0
# Process("ls").!
README.md
build.sbt
fileopen.txt
finance.csv
out
project
src
target
- exitCode2=0
 */
