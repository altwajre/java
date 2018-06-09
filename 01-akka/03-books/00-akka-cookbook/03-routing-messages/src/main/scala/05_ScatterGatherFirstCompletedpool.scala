import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.routing.ScatterGatherFirstCompletedPool
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/*
# Creating a ScatterGatherFirstCompletedPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/d0ce68da-c4ce-42a6-8fd3-c4e3575b1a74.xhtml

It sends the same message to all the actors, waits for the actor who first completes the work, and sends back a response.
It then discards replied from the other actors.

 */
class ScatterGatherFirstCompletedpoolActor extends Actor {
  override def receive: Receive = {
    case msg: String => sender ! s"I ${self.path.name} say hello back to you"
    case _ => println(s"I don't understand the message")
  }
}

object ScatterGatherFirstCompletedpool extends App {
  implicit val timeout = Timeout(10 seconds)
  private val system = ActorSystem("ScatterGatherFirstCompletedpool")
  private val router = system.actorOf(ScatterGatherFirstCompletedPool(5, within = 10.seconds).props(Props[ScatterGatherFirstCompletedpoolActor]))
  private val result = Await.result((router ? "hello").mapTo[String], 10 seconds)
  println(result)

  system.terminate()

}
/*
I $b say hello back to you
 */
