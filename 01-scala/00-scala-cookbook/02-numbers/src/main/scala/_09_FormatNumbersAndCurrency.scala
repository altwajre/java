import java.text.NumberFormat
import java.util.{Currency, Locale}

object _09_FormatNumbersAndCurrency {
  def main(args: Array[String]): Unit = {
    numbers

    currency
  }

  private def currency = {
    println("# Currency")
    val formatter = NumberFormat.getCurrencyInstance
    println(formatter.format(123.456789))
    println(formatter.format(1234.56789))

    val de = Currency.getInstance(new Locale("de", "DE"))
    formatter.setCurrency(de)
    println(formatter.format(123456.789))
  }

  private def numbers = {
    println("# Numbers")
    val pi = scala.math.Pi
    println(s"pi: $pi")

    println(f"$pi%1.5f")
    println(f"$pi%1.2f")

    println("# default us locale")
    val formatter = NumberFormat.getIntegerInstance
    println(formatter.format(10000))
    println(NumberFormat.getInstance().format(10000.33))

    println("# de locale")
    val locale = new Locale("de", "DE")
    val deFormatter = NumberFormat.getIntegerInstance(locale)
    println(deFormatter.format(10000))
  }
}
/*
# Numbers
pi: 3.141592653589793
3.14159
3.14
# default us locale
10,000
10,000.33
# de locale
10.000
# Currency
$123.46
$1,234.57
EUR123,456.79
 */
