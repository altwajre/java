package builder

/*
Implementation with a case class

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/334d77a0-411d-4e54-8add-5031912179dd.xhtml

The builder design pattern with case classes uses immutable fields and this is considered a good practice.

Drawback:
No validation
 */
case class Person(firstName: String = "", lastName: String = "", age: Int = 0)

object CaseClasses {
  def main(args: Array[String]) = {
    val person1 = Person("Tom", "Niko", 28)
    val person2 = Person("Harry")
    println(s"Person 1: ${person1}")
    println(s"Person 2: ${person2}")
  }
}
/*
Person 1: Person(Tom,Niko,28)
Person 2: Person(Harry,,0)
 */
