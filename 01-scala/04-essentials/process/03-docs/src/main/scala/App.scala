import scala.sys.process._

object App {
  def main(args: Array[String]): Unit = {

    val dirContents = "ls".!!
    println(dirContents)
  }
}
