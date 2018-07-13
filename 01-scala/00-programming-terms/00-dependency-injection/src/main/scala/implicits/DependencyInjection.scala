package implicits

/*
Dependency injection using implicits

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/ded5a286-6e4e-4311-ae94-6c7f435a180c.xhtml
 */
object DependencyInjection {

  case class Person(name: String, age: Int)

  trait DatabaseService {
    def getPeople(): List[Person]
  }

  class DatabaseServiceImpl extends DatabaseService {
    override def getPeople(): List[Person] = List(
      Person("Ivan", 10),
      Person("Maria", 15),
      Person("John", 20)
    )
  }

  // it requires DatabaseService implicitly available
  trait UserService {
    def getAverageAgeOfPeople()(implicit ds: DatabaseService): Double
  }

  class UserServiceImpl extends UserService {
    override def getAverageAgeOfPeople()(implicit ds: DatabaseService): Double = {
      val (s, c) = ds.getPeople().foldLeft((0, 0)) {
        case ((sum, count), person) =>
          (sum + person.age, count + 1)
      }
      s.toDouble / c.toDouble
    }
  }

  object di {
    implicit val databaseService = new DatabaseServiceImpl
    implicit val userService = new UserServiceImpl
  }

  import di._

  def main(args: Array[String]): Unit = {
    println(s"The average age: ${userService.getAverageAgeOfPeople()}")
  }
}
/*
The average age: 15.0
 */
