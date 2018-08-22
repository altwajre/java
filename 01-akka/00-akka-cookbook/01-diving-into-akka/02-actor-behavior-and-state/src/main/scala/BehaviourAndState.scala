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

class SummingActorWithConstructor(initialSum: Int) extends Actor {
  // state inside the actor
  var sum = 0

  // behavior which is applied on the state
  override def receive: Receive = {
    // receives message an integer
    case x: Int => sum = initialSum + sum + x
      println(s"initialSum: $initialSum")
      println(s"x: $x")
      println(s"SummingActorWithConstructor state as sum is $sum")
      // default message
    case _ => println("Unknown message")
  }
}

object BehaviourAndState extends App {
  private val system = ActorSystem("BehaviourAndState")

  // creating an actor inside the actor system
  private val summingActor: ActorRef = system.actorOf(Props[SummingActor], "summingactor")
  // print actor path
  println(summingActor.path)
  summingActor ! 8

  private val summingActorWithConstructor: ActorRef = system.actorOf(Props(classOf[SummingActorWithConstructor], 10), "summingactorwithconstructor")

  println(summingActorWithConstructor.path)
  summingActorWithConstructor ! 18

  system.terminate()
}
/*
akka://BehaviourAndState/user/summingactor
x: 8
SummingActor state as sum is 8
akka://BehaviourAndState/user/summingactorwithconstructor
initialSum: 10
x: 18
SummingActorWithConstructor state as sum is 28
 */
