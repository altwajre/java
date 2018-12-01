import scala.collection.mutable.ArrayBuffer

/*
Creating an Array Whose Size Can Change (ArrayBuffer)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s09.html
 */
object ArrayBufferApp {
  def main(args: Array[String]): Unit = {
    val customers = ArrayBuffer("Tom", "Dick")
    println(customers)

    println("# add one element")
    customers += "Harry"
    println(customers)

    println("# add multiple elements")
    customers += ("Lee", "Jen")
    println(customers)

    println("# add multiple element with Seq")
    customers ++= Seq("Al", "Paul")
    println(customers)

    println("# append multiple elements")
    customers.append("Wei", "Greg")
    println(customers)

    println("# remove one element")
    customers -= "Wei"
    println(customers)

    println("# remove multiple elements")
    customers -= ("Jen", "Al")
    println(customers)

    println("# remove multiple elements in other collection")
    customers --= Seq("Lee", "Paul")
    println(customers)
  }
}
