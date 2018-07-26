/*
Creating Singletons with object

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s06.html
 */
object _05_Singletons {
  object CashRegister {
    def open {
      println("opened")
    }
    def close: Unit = {
      println("closed")
    }
  }
  def main(args: Array[String]): Unit = {
    CashRegister.open
    CashRegister.close
  }
}
/*
opened
closed
 */
