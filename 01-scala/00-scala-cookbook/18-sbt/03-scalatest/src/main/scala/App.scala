case class Person(var name: String)

object App {
  def main(args: Array[String]): Unit = {
    val p = Person("Tom")
    println("Hello from " + p.name)
  }
}
