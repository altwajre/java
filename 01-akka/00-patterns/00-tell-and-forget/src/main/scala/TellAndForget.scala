import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class SummingActor extends Actor {
  // state inside the actor
  var sum = 0

  // behaviour which is applied on the state
  override def receive: Receive = {
    // receives message an integer
    case x: Int => sum = sum + x
      println(s"x: $x")
      println(s"SummingActor state as sum is $sum")
      // default message
    case _ => println("Unknown message")
  }
}

object TellAndForget extends App {
  private val system = ActorSystem("TellAndForget")

  // creating an actor inside the actor system
  private val summingActor: ActorRef = system.actorOf(Props[SummingActor], "summingactor")
  // print actor path
  println(summingActor.path)
  summingActor ! 8

  system.terminate()
}
/*
akka://BehaviourAndState/user/summingactor
x: 8
SummingActor state as sum is 8
 */
