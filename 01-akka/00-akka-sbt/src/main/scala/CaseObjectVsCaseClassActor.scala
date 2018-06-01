import akka.actor.{Actor, ActorRef, ActorSystem, Props}

trait MyMsg

// case object
case object Play extends MyMsg

class Player extends Actor {
  override def receive: Receive = {
    case Play =>
      println("Play Music...")
  }
}

// case class
case class Hello(msg: String) extends MyMsg

class Singer extends Actor {
  override def receive: Receive = {
    case Hello(msg) => {
      println(s"Hello $msg")
    }
  }
}

object ObjectTest extends App {
  // Create actor system
  private val system = ActorSystem("ObjectTest")

  // Create 'Player' actor
  private val player: ActorRef = system.actorOf(Props[Player], "player")
  // send Play object to player actor
  player ! Play
  // Play Music...

  // Create 'Singer' actor
  private val singer: ActorRef = system.actorOf(Props[Singer], "singer")
  // send Hello case class to singer actor
  singer ! Hello("awesome singer!")
  // Hello awesome singer!

  system.terminate()

}
