import Worker.Work
import akka.actor.{Actor, ActorRef, Props}

class RouterPool extends Actor {
  var routees: List[ActorRef] = _

  override def preStart(): Unit = {
    routees = List.fill(5) (
      context.actorOf(Props[Worker])
    )
  }
  override def receive: Receive = {
    case msg: Work => {
      println(s"${Thread.currentThread().getName}: I'm A Router and I received a Message...")
      println(routees.size)
      routees(util.Random.nextInt(routees.size)) forward msg
    }
  }
}
