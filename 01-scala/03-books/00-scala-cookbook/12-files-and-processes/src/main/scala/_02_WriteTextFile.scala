import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

/*
Writing Text Files

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s03.html
 */
object _02_WriteTextFile {
  def main(args: Array[String]): Unit = {

    val theDir = new File("./out")
    if(!theDir.exists()) {
      println("create the dir")
      theDir.mkdir
    }
    else
      println("out dir exists")

    println("# PrintWriter")
    val writer = new PrintWriter(new File("./out/PrintWriter.txt"))
    writer.write("PrintWriter")
    writer.close

    println("# FileWriter")
    val file = new File("./out/FileWriter.txt")
    val bufferedWriter = new BufferedWriter(new FileWriter(file))
    bufferedWriter.write("FileWriter")
    bufferedWriter.close
  }
}

/*
# PrintWriter
# FileWriter
 */
