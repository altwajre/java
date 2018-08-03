import java.io.File

/*
Listing Files in a Directory

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s09.html
 */
object _08_ListFilesInDirectory {
  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    }
    else {
      List[File]()
    }
  }

  def main(args: Array[String]): Unit = {
    val files = getListOfFiles("./src/main/scala")
    files.foreach(println)
  }
}

/*
./src/main/scala/_05_CsvFile.scala
./src/main/scala/_01_ReadTextFile.scala
./src/main/scala/_04_ProcessCharInFile.scala
./src/main/scala/_02_WriteTextFile.scala
./src/main/scala/_03_BinaryFile.scala
./src/main/scala/_07_Serialization.scala
./src/main/scala/ListFilesInDirectory.scala
./src/main/scala/_06_StringAsFile.scala
 */
