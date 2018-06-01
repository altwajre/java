import Checker.{BlackUser, CheckUser, WhiteUser}
import Storage.AddUser
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

// 2. Talking to an Actor
// - Recorder is handling NewUser message
// - Recorder ask Checker; Recorder waits for Checker to return answer (result)
// - Recorder tell Storage; Recorder tell and forget; not return result from Storage

case class User(username: String, email: String)

object Recorder {
  def props(checker: ActorRef, storage: ActorRef): Props =
    Props(new Recorder(checker, storage))

  sealed trait RecorderMsg

  // Recorder Messages
  case class NewUser(user: User) extends RecorderMsg

}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {

  import Recorder._
  import scala.concurrent.ExecutionContext.Implicits.global // NEED it to run
  implicit val timeout = Timeout(5 seconds)

  override def receive: Receive = {
    case NewUser(user) =>
      println(Thread.currentThread().getName + " Recorder.CheckUser # 2")
      // SENDER: Checker actor
      // checker ? Checker.CheckUser(user) map {
      val future = checker ? Checker.CheckUser(user)
      println("future: "+future.getClass.getTypeName)
      future map {
        case WhiteUser(user) =>
          println(Thread.currentThread().getName + " Recorder.AddUser # 4")
          storage ! AddUser(user)
        case BlackUser(user) =>
          println(s"Recorder: $user in the blacklist")
      }
  }
}

object Checker {

  sealed trait CheckerMsg

  // Checker Messages
  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse

  // Checker Responses
  case class BlackUser(user: User) extends CheckerMsg

  case class WhiteUser(user: User) extends CheckerMsg

}

class Checker extends Actor {
  val blackList = List(
    User("Adam", "adam@mail.com")
  )

  override def receive: Receive = {
    case CheckUser(user) if blackList.contains(user) =>
      println(s"Checker: $user in the blacklist")
      sender() ! BlackUser(user)
    case CheckUser(user) =>
      println(Thread.currentThread().getName + " Checker returns WhiteUser # 3")
      println(s"Checker: $user not in the blacklist")
      // SENDER: send WhiteUser to Checker actor
      sender() ! WhiteUser(user)
  }
}

object Storage {

  sealed trait StorageMsg

  // Storage Messages
  case class AddUser(user: User) extends StorageMsg

}

class Storage extends Actor {
  var users = List.empty[User]

  override def receive: Receive = {
    case AddUser(user) =>
      println(Thread.currentThread().getName + " Storage.AddUser # 5")
      println(s"Storage: $user added")
      users = user :: users
  }
}

object TalkToActor extends App {
  // Create the 'talk-to-actor' actor system
  private val system = ActorSystem("talk-to-actor")
  // Create the 'checker' actor
  private val checker: ActorRef = system.actorOf(Props[Checker], "checker")
  // Create the 'storage' actor
  private val storage: ActorRef = system.actorOf(Props[Storage], "storage")
  // Create the 'recorder' actor
  private val recorder: ActorRef = system.actorOf(Recorder.props(checker, storage), "recorder")
  // send NewUser Message to Recorder
  println(Thread.currentThread().getName + " TalkToActor Recorder.NewUser # 1")
  recorder ! Recorder.NewUser(User("Jon", "jon@packlt.com"))
  Thread.sleep(100)
  // shutdown system
  system.terminate()

}
