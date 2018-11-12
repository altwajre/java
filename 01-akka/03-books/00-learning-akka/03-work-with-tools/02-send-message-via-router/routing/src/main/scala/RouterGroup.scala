import Worker.Work
import akka.actor.Actor

class RouterGroup(routees: List[String]) extends Actor {
  override def receive: Receive = {
    case msg: Work => {
      println("I'm a Router Group and I receive Work Message...")
      context.actorSelection(routees(util.Random.nextInt(routees.size))) forward msg
    }
  }
}
