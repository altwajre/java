package import_objectname

object MyObject {
  def helloFromMyObject() = {
    println("Hello from Import MyObject")
  }
}

object ImportObjectApp {
  import MyObject._

  def main(args: Array[String]): Unit = {
    helloFromMyObject()
  }
}
/*
Hello from Import MyObject
 */
