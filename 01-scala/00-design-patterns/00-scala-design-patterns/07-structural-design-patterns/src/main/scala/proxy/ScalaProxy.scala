package proxy

import java.io.{BufferedReader, InputStreamReader}

import scala.collection.JavaConverters._

/*
The proxy design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/3740eea4-2b42-4ab0-8069-c04e6baf0166.xhtml

The purpose of the proxy design pattern is to provide an interface to something else that then gets served behind the scenes to the user.

Provide access control to objects.

 */

object ScalaProxy {
  trait FileReader {
    def readFileContents(): String
  }

  class FileReaderReal(filename: String) extends FileReader {
    val contents = {
      val stream = this.getClass.getResourceAsStream(filename)
      val reader = new BufferedReader(
        new InputStreamReader(
          stream
        )
      )
      try {
        reader.lines().iterator().asScala.mkString(System.getProperty("line.separator"))
      } finally {
        reader.close()
        stream.close()
      }
    }

    println(s"Finished reading the actual file: $filename")

    override def readFileContents(): String = contents
  }

  class FileReaderProxy(filename: String) extends FileReader {
    private var fileReader: FileReaderReal = null

    override def readFileContents(): String = {
      if (fileReader == null) {
        fileReader = new FileReaderReal(filename)
      }
      fileReader.readFileContents()
    }
  }


  def main(args: Array[String]) = {
    val fileMap = Map(
      "file1.txt" -> new FileReaderProxy("file1.txt"),
      "file2.txt" -> new FileReaderProxy("file2.txt"),
      "file3.txt" -> new FileReaderProxy("file3.txt"),
      "file4.txt" -> new FileReaderReal("file1.txt")
    )
    println("Created the map. You should have seen file1.txt read because it wasn't used in a proxy.")
    println(s"Reading file1.txt from the proxy: ${fileMap("file1.txt").readFileContents()}")
    println(s"Reading file3.txt from the proxy: ${fileMap("file3.txt").readFileContents()}")
  }
}
/*
Finished reading the actual file: file1.txt
Created the map. You should have seen file1.txt read because it wasn't used in a proxy.
Finished reading the actual file: file1.txt
Reading file1.txt from the proxy: I am file 1.
Finished reading the actual file: file3.txt
Reading file3.txt from the proxy: I am file 3.
 */
