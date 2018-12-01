import akka.pattern.ask
import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/*
Sending a Message to an Actor and Waiting for a Reply

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s11.html

- Send a message to an actor using either ? or ask instead of the usual ! method
- The ? and ask methods create a Future, so you use Await.result to wait for the response from the other actor.
 */
object _10_AskActor {
  case object AskNameMessage
  class TestActor extends Actor {
    override def receive: Receive = {
      // respond to the 'ask' request
      case AskNameMessage => sender ! "Actor said: Yes, I am happy."
      case _ => println("Unknown")
    }
  }
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("AskActorSystem")
    val actor = system.actorOf(Props[TestActor], name = "actor")

    // (1) this is one way to "ask" another another for information
    implicit val timeout = Timeout(2 seconds)
    val future = actor ? AskNameMessage
    val result = Await.result(future, timeout.duration).asInstanceOf[String]
    println(result)

    // (2) a slightly different way to ask another actor for information
    val future2: Future[String] = ask(actor, AskNameMessage).mapTo[String]
    val result2 = Await.result(future2, 1 second)
    println(result2)

    system.terminate
  }
}
/*
Actor said: Yes, I am happy.
Actor said: Yes, I am happy.
 */
