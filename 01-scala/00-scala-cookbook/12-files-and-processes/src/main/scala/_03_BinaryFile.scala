import java.io.{File, FileInputStream, FileOutputStream, IOException}

/*
Reading and Writing Binary Files

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s04.html
 */
object _03_BinaryFile {
  def main(args: Array[String]): Unit = {
    val theDir = new File("./out")
    if(!theDir.exists()) {
      println("create the dir")
      theDir.mkdir
    }
    else
      println("out dir exists")

    var in = None: Option[FileInputStream]
    var out = None: Option[FileOutputStream]

    try {
      in = Some(new FileInputStream("fileopen.txt"))
      out = Some(new FileOutputStream("./out/fileopen.txt.copy"))
      var c = 0
      while ( {
        c = in.get.read; c != -1
      }) {
        out.get.write(c)
      }
    }
    catch {
      case e: IOException => e.printStackTrace
    }
    finally {
      println("entered finally ...")
      if (in.isDefined) in.get.close
      if (out.isDefined) out.get.close
    }
  }
}
/*
entered finally ...
 */
