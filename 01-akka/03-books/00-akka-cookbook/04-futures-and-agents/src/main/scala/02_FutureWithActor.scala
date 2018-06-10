import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/*
Using futures with actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/c41e2507-9de7-45c0-b421-11c74d25fd97.xhtml

How it works...

We have created an actor that calculates the sum, and we are using ? mark to send the message to the actor and returns a future validator.
We don't need Executioncontext explicitly as it is provided by the actor system itself.

 */
class ComputationActor extends Actor {
  override def receive: Receive = {
    case (a: Int, b: Int) => sender ! (a + b)
  }
}

object FutureWithActor extends App {
  implicit val timeout = Timeout(10 seconds)
  private val system = ActorSystem("FutureWithActor")
  private val actor: ActorRef = system.actorOf(Props[ComputationActor])
  private val future = (actor ? (2, 3)).mapTo[Int]
  private val sum = Await.result(future, 10 seconds)
  println(s"Future Result $sum")

  system.terminate()
}
/*
Future Result 5
 */
