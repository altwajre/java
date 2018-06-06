import UserStorage.{Connect, Disconnect, Operation}
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Stash}

case class User(username: String, email: String)

// User Storage
// Connect to database
// Disconnect to database
// CRUD operations
object UserStorage {
  trait DBOperation
  object DBOperation {
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(dBOperation: DBOperation, user: Option[User])
}

class UserStorage extends Actor with Stash {
  override def receive: Receive = disconnected

  def connected: Actor.Receive = {
    case Disconnect => {
      println("User Storage Disconnect from DB")
      context.unbecome()
    }
    case Operation(op, user) => {
      println(s"User Storage receive ${op} to do in user: ${user}")
    }
  }

  def disconnected: Actor.Receive = {
    case Connect => {
      println("User Storage connected to DB")
      unstashAll() // need to unstash-all before switch to connected
      context.become(connected)
    }
    case _ => {
      // when Create operation before Connect State, use stash
      // default state - stash the messages
      stash()
    }
  }
}

object BecomeHotswap extends App {
  import UserStorage._

  private val system = ActorSystem("BecomeHotswap")
  private val userStorage: ActorRef = system.actorOf(Props[UserStorage], "userStorage")

//  userStorage ! Connect

  userStorage ! Operation(DBOperation.Create, Some(User("Admin", "admin@gmail.com")))
  userStorage ! Connect
  userStorage ! Disconnect

  Thread.sleep(100)

  system.terminate()

}
