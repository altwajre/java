/*
Case Classes

https://docs.scala-lang.org/tour/case-classes.html
 */

case class Book(isbn: String)

object CaseClasses {
  def main(args: Array[String]) = {
    println("# Defining a case class")
    val history = Book("12345")
    println(history.isbn)

    println("# Comparison: compare by structure, not by reference")
    case class Person(name: String, age: Int)
    val tom1 = Person("Tom", 18)
    val tom2 = Person("Tom", 18)
    val isEqual = tom1 == tom2
    println(s"tom1 equal to tom2? $isEqual")

    println("# Copying: shallow copy - can optionally change args")
    val tomCopy = tom1.copy(name = tom1.name, age = 28)
    println(s"Copied Tom: $tomCopy")
  }
}
/*
# Defining a case class
12345
# Comparison: compare by structure, not by reference
tom1 equal to tom2? true
# Copying: shallow copy
Copied Tom: Person(Tom,28)
 */
