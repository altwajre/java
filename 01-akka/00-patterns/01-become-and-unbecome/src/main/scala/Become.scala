
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

// https://doc.akka.io/docs/akka/2.5/actors.html#become-unbecome

class HotSwapActor extends Actor {
  import context._
  def angry: Receive = {
    case "foo" => {
      println("already angry")
      sender ! "I am already angry?"
    }
    case "bar" => {
      println("become happy")
      sender ! "becoming happy"
      context.become(happy)
    }
  }

  def happy: Receive = {
    case "bar" => {
      println("already happy")
      sender ! "I am already happy :-)"
    }
    case "foo" => {
      println("become angry")
      sender ! "becoming angry"
      context.become(angry)
    }
  }

  override def receive: Receive = {
    case "foo" => {
      println("foo")
      sender ! "default receive becoming angry"
      context.become(angry)
    }
    case "bar" => {
      println("bar")
      context.become(happy)
    }
  }
}

object BecomeHotswap extends App {
  implicit val timeout = Timeout(2 seconds)

  private val system = ActorSystem("BecomeHotswap")
  private val actor: ActorRef = system.actorOf(Props[HotSwapActor], "hotSwapActor")

  private val angryFuture: Future[String] = (actor ? "foo").mapTo[String]
  private val angryResult: String = Await.result(angryFuture, 2 seconds)
  println(angryResult)

  private val happyFuture: Future[String] = (actor ? "bar").mapTo[String]
  private val happyResult: String = Await.result(happyFuture, 2 seconds)
  println(happyResult)
  actor ! "foo"

  Thread.sleep(1000)

  system.terminate()

}
