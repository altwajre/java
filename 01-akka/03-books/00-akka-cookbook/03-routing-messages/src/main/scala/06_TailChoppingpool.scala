import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.routing.TailChoppingPool
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/*
# Creating a TailChoppingPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/c3b2a769-63b5-4481-8451-23d12a9610bd.xhtml

TailChoppingPool first sends the message to one, randomly picked, routee, and then, after a small delay, to a second routee (picked randomly from the remaining routees), and so on.
It waits for the first reply it receives, and forwards it back to the original sender. Other replies are discarded.
 */
class TailChoppingActor extends Actor {
  override def receive = {
    case msg: String => sender ! s"I ${self.path.name} say hello back to you"
    case _ => println(s"I don't understand the message")
  }
}

object TailChoppingpoolApp extends App {
  implicit val timeout = Timeout(10 seconds)
  private val system = ActorSystem("TailChoppingpool")
  private val router = system.actorOf(TailChoppingPool(5, within = 10.seconds, interval = 20.millis).props(Props[TailChoppingActor]))
  private val result = Await.result((router ? "hello").mapTo[String], 10 seconds)
  println(result)

  system.terminate()
}
/*
I $c say hello back to you
 */
