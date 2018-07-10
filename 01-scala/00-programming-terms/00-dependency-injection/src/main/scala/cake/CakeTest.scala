package cake

import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

/*
Unit testing our application

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/6a7771f4-67d6-4aa2-9bf0-9733864835bd.xhtml

We use Mockito to simulate the database access.

 */

trait TestEnvironment
  extends UserComponent
    with DaoComponent
    with DatabaseComponent
    with MigrationComponent
    with MockitoSugar {
  override val dao: Dao = mock[Dao]
  override val databaseService: DatabaseService = mock[DatabaseService]
  override val migrationService: MigrationService = mock[MigrationService]
  override val userService: UserService = mock[UserService]
}

@RunWith(classOf[JUnitRunner])
class UserComponentTest extends FlatSpec with Matchers with MockitoSugar with TestEnvironment {
  val className = "A"
  val emptyClassName = "B"
  val people = List(
    Person(1, "a", 10),
    Person(2, "b", 15),
    Person(3, "c", 20)
  )

  override val userService = new UserService

  when(dao.getPeopleInClass(className)).thenReturn(people)
  when(dao.getPeopleInClass(emptyClassName)).thenReturn(List())

  "getAverageAgeOfUsersInClass" should "properly calculate the average of all ages." in {
    userService.getAverageAgeOfUsersInClass(className) should equal(15.0)
  }

  it should "properly handle an empty result." in {
    userService.getAverageAgeOfUsersInClass(emptyClassName) should equal(0.0)
  }
}
