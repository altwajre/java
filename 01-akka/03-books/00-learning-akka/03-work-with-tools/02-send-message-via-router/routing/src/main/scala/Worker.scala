import akka.actor.Actor

object Worker {
  case class Work()
}

class Worker extends Actor {
  import Worker._

  override def receive: Receive = {
    case msg: Work => {
      println(s"${Thread.currentThread().getName}: I received Work Message and My ActorRef: ${self}")
    }
  }
}
