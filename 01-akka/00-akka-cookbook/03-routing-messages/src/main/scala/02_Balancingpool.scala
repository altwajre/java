import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.BalancingPool

/*
# Creating a BalancingPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/5ea780f3-a105-482b-a5b2-4f70112098a2.xhtml

A BalancingPool tries to redistribute the work among the actors for performance improvement.
In the BalancingPool, all actors share the same mailbox.
 */
class BalancingPoolActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"I am ${self.path.name}")
    case _ => println(s"I don't understand the message")
  }
}

object Balancingpool extends App {
  private val system = ActorSystem("Balancingpool")
  private val router: ActorRef = system.actorOf(BalancingPool(5).props(Props[BalancingPoolActor]))
  for(i <- 1 to 8) {
    router ! s"Hello $i"
  }

  system.terminate()
}
/*
I am $e
I am $c
I am $e
I am $b
I am $d
I am $a
I am $e
I am $c
 */
