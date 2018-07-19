package builder

/*
Using require statements

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/d32023a6-3d36-41e7-99e5-0087d0edecb9.xhtml

RuntimeException occurs when required field is missing
 */
object CaseRequire {
  case class Person(firstName: String="", lastName: String="", age: Int = 0) {
    require(firstName != "", "First name is required.")
    require(lastName != "", "Last name is required.")
  }

  def main(args: Array[String]) = {
    val person1 = Person("Tom", "Lee", 28)
    println(s"Person 1: ${person1}")

    try {
      val person2 = Person(firstName = "John")
      println(s"Person 2: ${person2}")
    }
    catch {
      case e: Throwable => e.printStackTrace()
    }
  }
}
/*
Person 1: Person(Tom,Lee,28)
java.lang.IllegalArgumentException: requirement failed: Last name is required.
	at scala.Predef$.require(Predef.scala:277)
	at builder.CaseRequire$Person.<init>(CaseRequire.scala:12)
	at builder.CaseRequire$.main(CaseRequire.scala:20)
	at builder.CaseRequire.main(CaseRequire.scala)
 */
