import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.{DefaultResizer, RoundRobinPool}

/*
Creating a dynamically resizable pool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/e878118e-72cc-403c-9601-dc049c4fcd10.xhtml

There are situations when we want a variable number of actors under the router to handle the increasing and decreasing number of request.
 */
case object Load

class LoadActor extends Actor {
  override def receive: Receive = {
    case Load => println("Handling loads of requests")
  }
}

object ResizablePoolApp extends App {
  private val system = ActorSystem("ResizablePool")
  private val resizer = DefaultResizer(lowerBound = 2, upperBound = 15)
  private val router: ActorRef = system.actorOf(RoundRobinPool(5, Some(resizer)).props(Props[LoadActor]))

  router ! Load
  router ! Load

  system.terminate()

}
/*
Handling loads of requests
Handling loads of requests
 */
