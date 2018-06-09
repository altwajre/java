import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.BroadcastPool

/*
# Creating a BroadcastPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/c7bbfeaa-9c8b-45d5-8fdd-1697ba68017f.xhtml

Send a common command to all the actors to do the same work

We sent a message "broadcast" to the router, and it sent the same message to all the actors under it
 */
class BroadcastPoolActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"Receive $msg, I am ${self.path.name}")
    case _ => println(s"I don't understand the message")
  }
}

object BroadcastpoolApp extends App {
  private val system = ActorSystem("Broadcastpool")
  private val router: ActorRef = system.actorOf(BroadcastPool(5).props(Props[BroadcastPoolActor]))
  router ! "broadcast"

  system.terminate()
}
/*
Receive broadcast, I am $b
Receive broadcast, I am $d
Receive broadcast, I am $e
Receive broadcast, I am $c
Receive broadcast, I am $a
 */
