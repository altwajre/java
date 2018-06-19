package self_types
import scala.collection.mutable

/*
Using self types

Self types are often used for dependency injection.

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/188cfe62-bd87-4b9a-9b77-8f68e31dad3c.xhtml

The persist method will do some transformations on the data and then insert it in our database.
The database implementations are separated.
 */

/*
Naming the self type

In the preceding code, we included our self type using the statement - this: Database[T] =>.
This allows us to access the methods of our included types directly as if they were methods of the trait includes them.
Another way of doing the same here is by writing self: Database[T] => instead.
Calling the methods of the injected dependencies using this approach,
however, would require the developer to use self in order to gain access to the required methods.

Using the with keyword, we can add as many requirements as we like.
 */
trait Persister[T] {
  // this: self types
  this: Database[T] with History =>
  def persist(data: T) = {
    println("Calling persist.")
    save(data) // Database.save()
    add() // History.add()
  }
}

trait Database[T] {
  def save(data: T)
}

trait History {
  def add() = {
    println("Action added to history.")
  }
}

trait MemoryDatabase[T] extends Database[T] {
  val db: mutable.MutableList[T] = mutable.MutableList.empty

  override def save(data: T): Unit = {
    println("Saving to in memory database.")
    db.+=:(data)
  }
}

trait FileDatabase[T] extends Database[T] {
  override def save(data: T): Unit = {
    println("Saving to file.")
  }
}

// dependency injection
class FilePersister[T] extends Persister[T] with FileDatabase[T] with History
class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History

object SelfTypes {
  def main(args: Array[String]) = {
    val fileStringPersister = new FilePersister[String]
    val memoryIntPersister = new MemoryPersister[Int]

    fileStringPersister.persist("Something")
    fileStringPersister.persist("Something else")

    memoryIntPersister.persist(100)
    memoryIntPersister.persist(123)
  }
}
/*
Calling persist.
Saving to file.
Action added to history.
Calling persist.
Saving to file.
Action added to history.
Calling persist.
Saving to in memory database.
Action added to history.
Calling persist.
Saving to in memory database.
Action added to history.
 */
