import scala.io.Source

/*
How to Process a CSV File

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch12s06.html
 */
object AccessArrayElements {
  def main(args: Array[String]): Unit = {
    println("Month, Income, Expenses, Profit")
    val bufferedSource = Source.fromFile("finance.csv")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      println(cols.getClass.getSimpleName)
      // do whatever you want with the columns
      println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
    }
    bufferedSource.close
  }
}
/*
Month, Income, Expenses, Profit
String[]
January|10000.00|9000.00|1000.00
String[]
February|11000.00|9500.00|1500.00
String[]
March|12000.00|10000.00|2000.00
 */

object NamedVariables {
  def main(args: Array[String]): Unit = {
    println("Month, Income, Expenses, Profit")
    val bufferedSource = Source.fromFile("finance.csv")
    for (line <- bufferedSource.getLines) {
      val Array(month, revenue, expenses, profit) = line.split(",").map(_.trim)
      // do whatever you want with the columns
      println(s"$month|$revenue|$expenses|$profit")
    }
    bufferedSource.close
  }
}
/*
Month, Income, Expenses, Profit
January|10000.00|9000.00|1000.00
February|11000.00|9500.00|1500.00
March|12000.00|10000.00|2000.00
 */

// If you need to drop the header line - bufferedSource.getLines.drop(1)
object DropFirstLine {
  def main(args: Array[String]): Unit = {
    println("Month, Income, Expenses, Profit")
    val bufferedSource = Source.fromFile("finance.csv")
    for (line <- bufferedSource.getLines.drop(1)) {
      val Array(month, revenue, expenses, profit) = line.split(",").map(_.trim)
      // do whatever you want with the columns
      println(s"$month|$revenue|$expenses|$profit")
    }
    bufferedSource.close
  }
}
/*
Month, Income, Expenses, Profit
February|11000.00|9500.00|1500.00
March|12000.00|10000.00|2000.00
 */
