import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class FibonacciActor extends Actor {
  override def receive: Receive = {
    case num : Int => {
      val fibonacciNumber = fib(num)
      sender ! fibonacciNumber
    }
    case str : String => {
      val result = toUpper(str)
      // Actor knows who (sender) has sent them the message
      sender ! result
    }
    case _ => println("Unknown message")
  }

  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib(n - 1) + fib(n - 2)
  }

  def toUpper(s: String): String = {
    s.toUpperCase
  }
}

object AskResultFromActor extends App {
  implicit val timeout = Timeout(10 seconds)
  val system = ActorSystem("AskResultFromActor")
  val actor = system.actorOf(Props[FibonacciActor])

  // asking for result from actor
  // send a message with question mark (?), it returns a future promising that you will get the result when the operation is completed.
  val fibFuture: Future[Int] = (actor ? 10).mapTo[Int]
  val fiboacciNumber = Await.result(fibFuture, 10 seconds)
  println(fiboacciNumber)

  private val toUpperFuture: Future[String] = (actor ? "hello").mapTo[String]
  private val toUpperResult: String = Await.result(toUpperFuture, 10 seconds)
  println(toUpperResult)

  system.terminate()

}
/*
55
HELLO
 */
