import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.routing.{Broadcast, RandomPool}

/*
Sending specially handled messages to routers

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/259c965d-d3e9-48f2-a3ae-a1cc1164af51.xhtml

There are some kinds of messages that are specially handled by actors. They have a special behavior.
These messages are mostly used for handling management of the actors.

- Broadcast messages
- PoisonPill messages
- Kill messages
- Management messages such as AddRoutee, RemoveRoutee, and so on

How it works...
The same message can be sent to all the actors by wrapping the Handle message into the Broadcast envelope.
After that, we sent PoisonPill, which is a specially handled message in the Broadcast envelope, to kill all the actors under the router.
 */
case object Handle

class SpeciallyHandledActor extends Actor {
  override def receive: Receive = {
    case Handle => println(s"${self.path.name} says hello")
  }
}

object SpeciallyHandledApp extends App {
  private val system = ActorSystem("SpeciallyHandled")
  private val router: ActorRef = system.actorOf(RandomPool(5).props(Props[SpeciallyHandledActor]))
  router ! Broadcast(Handle)
  router ! Broadcast(PoisonPill)
  router ! Handle

  system.terminate()

}
/*
$a says hello
$c says hello
$d says hello
$b says hello
$e says hello
[INFO] [06/08/2018 21:58:07.470] [SpeciallyHandled-akka.actor.default-dispatcher-9] [akka://SpeciallyHandled/user/$a/$c] Message [Handle$] from Actor[akka://SpeciallyHandled/deadLetters] to Actor[akka://SpeciallyHandled/user/$a/$c#-308588665] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
 */
