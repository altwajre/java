import java.io._

/*
Using Serialization

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s08.html
 */


object _07_Serialization {

  // create a serializable Stock class
  @SerialVersionUID(123L)
  class Stock(var symbol: String, var price: BigDecimal) extends Serializable {
    override def toString: String = f"$symbol%s is ${price.toDouble}%.2f"
  }

  def main(args: Array[String]): Unit = {
    val theDir = new File("./out")
    if (!theDir.exists()) {
      println("create the dir")
      theDir.mkdir
    }
    else
      println("out dir exists")

    println("# (1) create a Stock instance")
    val nflx = new Stock("NFLX", BigDecimal(85.00))

    println("# (2) write the instance out to a file")
    val oos = new ObjectOutputStream(new FileOutputStream("./out/nflx"))
    oos.writeObject(nflx)
    oos.close

    println("# (3) read the object back in")
    val ois = new ObjectInputStream(new FileInputStream("./out/nflx"))
    val stock = ois.readObject.asInstanceOf[Stock]
    ois.close

    println("# (4) print the object that was read back in")
    println(stock)
  }
}
/*
out dir exists
# (1) create a Stock instance
# (2) write the instance out to a file
# (3) read the object back in
# (4) print the object that was read back in
NFLX is 85.00
 */
