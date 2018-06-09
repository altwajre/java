import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.RandomPool

/*
Creating a RandomPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/01327008-9861-4393-8de5-b63af52c0280.xhtml

A random pool of actors does nothing more than send messages to a randomly picked routee among a list routees.
It is useful when you don't case which routee picks up the message and does the work for you,
and it may be the case that you end up sending the message to the busiest actor.
 */
class RandomPoolActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"I am ${self.path.name}")
    case _ => println(s" I don't understand the message")
  }
}

object RandomPoolApp extends App {
  private val system = ActorSystem("RandomPool")
  private val router: ActorRef = system.actorOf(RandomPool(5).props(Props[RandomPoolActor]))
  for (i <- 1 to 5) {
    router ! s"Hello $i"
  }

  system.terminate()
}
/*
I am $a
I am $b
I am $c
I am $d
I am $d
 */
