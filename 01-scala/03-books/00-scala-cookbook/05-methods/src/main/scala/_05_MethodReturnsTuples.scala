/*
Defining a Method That Returns Multiple Items (Tuples)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s06.html

Returns multiple values from a method
 */
object _05_MethodReturnsTuples {
  def getStockInfo = {
    ("NFLX", 100.00, 101.00) // this is a Tuple3
  }
  def main(args: Array[String]): Unit = {
    val (symbol, currentPrice, bidPrice) = getStockInfo
    println((symbol, currentPrice, bidPrice))
  }
}
/*
(NFLX,100.0,101.0)
 */
