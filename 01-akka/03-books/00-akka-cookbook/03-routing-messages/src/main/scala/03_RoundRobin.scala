import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool

/*
# Creating a RoundRobinPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/d2342737-2d94-4434-a0b4-272bf1f27082.xhtml

A RoundRobinPool is a group of the same type of actors and resembles the property whereby messages are delivered one by one to all the actors in the loop.

How it works...

We implement five RoundRobinPool actors.
We sent five messages to the actor.
Each goes to a different actor, one by one, in a loop.

 */
class RoundRobinPoolActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"I am ${self.path.name}")
    case _ => println(s"I don't understand the message")
  }
}

object RoundRobinApp extends App {
  private val system = ActorSystem("RoundRobin")
  private val router: ActorRef = system.actorOf(RoundRobinPool(5).props(Props[RoundRobinPoolActor]))
  for (i <- 1 to 8) {
    router ! s"Hello $i"
  }

  system.terminate()
}
/*
I am $d
I am $e
I am $a
I am $b
I am $c
I am $b
I am $c
I am $a
 */
