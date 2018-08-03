import java.io.File

/*
Listing Subdirectories Beneath a Directory

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s10.html
 */
object _09_ListSubDirsInDirectory {
  // assumes the dir exists
  def getListOfSubDirectories(dir: File): List[String] = {
    dir.listFiles
      .filter(_.isDirectory)
      .map(_.getName)
      .toList
  }
  def main(args: Array[String]): Unit = {
    val subDirs = getListOfSubDirectories(new File("./src"))
    subDirs.foreach(println)
  }
}
/*
test
main
 */
