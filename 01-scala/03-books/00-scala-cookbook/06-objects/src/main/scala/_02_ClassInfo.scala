/*
The Scala Equivalent of Java’s .class

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s03.html
 */
object _02_ClassInfo {
  case class Person(name: String)
  def main(args: Array[String]): Unit = {
    val classOfPerson = classOf[Person]
    println(s"classOfPerson.getSimpleName: ${classOfPerson.getSimpleName}")
    println(s"Person.getClass.getSimpleName: ${Person.getClass.getSimpleName}")
  }
}
/*
classOfPerson.getSimpleName: Person
Person.getClass.getSimpleName: Person$
 */
