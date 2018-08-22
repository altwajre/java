import akka.actor.{Actor, ActorRef, ActorSystem, Cancellable, Props}

import scala.concurrent.duration._

case object Foo3

/*
Canceling a scheduled operation of the actor

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/a5fc8cae-af7e-4931-8659-a58302ae7069.xhtml

It is necessary to cancel the scheduler if it has reached a certain condition.

How it works...

When create the scheduler, it can be assigned to the Cancellable interface type, which exposes the cancel method.
Thus, we can cancel the scheduler.
 */
class CancelOperation extends Actor {
  var i = 3

  override def receive: Receive = {
    case "tick" => {
      println(s"Do the same task again")
      i = i - 1
      if (i == 0) {
        println("Cancel operation")
        Scheduler.cancellable.cancel()
      }
    }
  }
}

object Scheduler extends App {
  private val system = ActorSystem("Scheduler")

  import system.dispatcher

  private val actor: ActorRef = system.actorOf(Props[CancelOperation])
  val cancellable: Cancellable = system.scheduler.schedule(0 seconds, 1 seconds, actor, "tick")

  Thread.sleep(3500)
  system.terminate()
}
/*
Do the same task again
Do the same task again
Do the same task again
Cancel operation
 */
