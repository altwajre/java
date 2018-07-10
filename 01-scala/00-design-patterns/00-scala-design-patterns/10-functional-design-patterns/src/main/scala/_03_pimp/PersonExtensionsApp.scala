package _03_pimp

case class Person(name: String, age: Int)

object PersonExtensionsApp {

  implicit class PersonSeqExtensions(val seq: Iterable[Person]) extends AnyVal {
    def saveToDatabase(): Unit = {
      seq.foreach {
        case person =>
          println(s"Saved: ${person} to the database")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val people = List(
      Person("Tom", 28),
      Person("Dick", 18),
      Person("Harry", 38)
    )
    people.saveToDatabase()
  }
}
/*
Saved: Person(Tom,28) to the database
Saved: Person(Dick,18) to the database
Saved: Person(Harry,38) to the database
 */
