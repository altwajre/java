
object HigherOrderFunctions {
  def main(args: Array[String]) = {
    val salaries = Seq(1, 2, 3)
    val doubleSalary = (x: Int) => x * 2
    // map takes a function as parameter
    val newSalaries = salaries.map(doubleSalary)
    println(newSalaries)
  }

}
