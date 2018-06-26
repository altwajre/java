/*
Higher Order Functions

https://docs.scala-lang.org/tour/higher-order-functions.html
 */

case class WeeklyWeatherForecast(temperatures: Seq[Double]) {
  private def convertCtoF(temp: Double) = temp * 1.8 + 32
  def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF)
}

object HigherOrderFunctions {
  def main(args: Array[String]) = {
    println("# Pass function as parameter")
    val salaries = Seq(1, 2, 3)
    val doubleSalary = (x: Int) => x * 2
    // map takes a function as parameter
    val newSalaries = salaries.map(doubleSalary)
    println(newSalaries)

    println("# Coercing methods into functions")
    // pass methods as arguments because Scala compiler will coerce the method into a function

    val forecast = WeeklyWeatherForecast(Seq(1,2,3))
    val fahrenheit = forecast.forecastInFahrenheit
    println(fahrenheit)

    println("# Functions that return functions")
    def urlBuilder(ssl: Boolean, domainNAme: String): (String, String) => String = {
      val schema = if (ssl) "https://" else "http://"
      (endpoint: String, query: String) => s"$schema$domainNAme/$endpoint?$query"
    }

    val domainName = "www.example.com"
    def getURL = urlBuilder(ssl=true, domainName)
    val endpoint = "Users"
    val query = "id=1"
    val url = getURL(endpoint, query)
    println(url)
  }
}
/*
# Pass function as parameter
List(2, 4, 6)
# Coercing methods into functions
List(33.8, 35.6, 37.4)
# Functions that return functions
https://www.example.com/Users?id=1
 */
