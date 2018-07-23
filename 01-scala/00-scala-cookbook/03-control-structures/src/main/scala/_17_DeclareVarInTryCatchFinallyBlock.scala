import java.io.{FileInputStream, FileOutputStream, IOException}

/*
Declaring a Variable Before Using It in a try/catch/finally Block

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s18.html
 */
object _17_DeclareVarInTryCatchFinallyBlock {
  def main(args: Array[String]): Unit = {
    var in = None: Option[FileInputStream]
    var out = None: Option[FileOutputStream]

    try {
      in = Some(new FileInputStream("/tmp/Test.class"))
      out = Some(new FileOutputStream("/tmp/Test.class.copy"))
      var c = 0
      while({c = in.get.read; c != -1}) {
        out.get.write(c)
      }
    }
    catch {
      case e: IOException => e.printStackTrace
    }
    finally {
      println("# Entered finally ...")
      if (in.isDefined) in.get.close
      if (out.isDefined) out.get.close
    }
  }
}
/*
java.io.FileNotFoundException: /tmp/Test.class (No such file or directory)
	at java.io.FileInputStream.open0(Native Method)
	at java.io.FileInputStream.open(FileInputStream.java:195)
	at java.io.FileInputStream.<init>(FileInputStream.java:138)
	at java.io.FileInputStream.<init>(FileInputStream.java:93)
	at DeclareVarInTryCatchFinallyBlock$.main(DeclareVarInTryCatchFinallyBlock.scala:14)
	at DeclareVarInTryCatchFinallyBlock.main(DeclareVarInTryCatchFinallyBlock.scala)
# Entered finally ...
 */
