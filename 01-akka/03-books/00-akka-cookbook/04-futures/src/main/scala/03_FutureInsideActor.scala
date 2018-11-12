import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/*
Using futures inside actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/4a4eea3c-f671-4422-85f7-21e18d3ccba1.xhtml

Use a future inside an actor to schedule an operation asynchronously to another thread.

How it works...

We are using future as we have used it in other places.
The difference is that we got ExecutionContext by importing context.dispatcher inside the actor, which is the default dispatcher in Akka.
We are using Await.result to retrieve a result from the future, and it blocks the thread inside the actor, which is not recommended.
Instead, we should use a callback function, such as onComplete.
 */
class FutureActor extends Actor {
  import context.dispatcher
  override def receive: Receive = {
    case (a: Int, b: Int) => {
      val f = Future(a + b)
      val sum = Await.result(f, 10 seconds)
      println(s"Actor: sum=$sum")
    }
  }
}

object FutureInsideActor extends App {
  private val system = ActorSystem("FutureInsideActor")
  private val fActor: ActorRef = system.actorOf(Props[FutureActor])
  fActor ! (2, 3)

  system.terminate()
}
/*
Actor: sum=5
 */
