import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}

case object Stop {}

class ShutdownActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"ShutdownActor: $msg")
    case Stop => context.stop(self)
  }
}

/*
PoisonPill and context.stop(self) are the two ways to kill an actor.

PoisonPill is the inbuilt message that is handled after all the messages that were already queued in the mailbox
Context.stop is basically used for an ordered shutdown of actors when you want the child actors to stop first, then the parent actor,
followed by the ActorSystem to stop top-level actors.
 */
object Shutdown extends App {
  private val system = ActorSystem("Shutdown")
  private val shutdownActor1: ActorRef = system.actorOf(Props[ShutdownActor], "shutdownActor1")
  shutdownActor1 ! "hello 1"
  shutdownActor1 ! PoisonPill
  shutdownActor1 ! "Are you there? 1"

  private val shutdownActor2: ActorRef = system.actorOf(Props[ShutdownActor], "shutdownActor2")
  shutdownActor2 ! "hello 2"
  shutdownActor2 ! Stop
  shutdownActor2 ! "Are you there? 2"
}
/*
ShutdownActor: hello 2
ShutdownActor: hello 1
[INFO] [06/06/2018 17:14:20.192] [Shutdown-akka.actor.default-dispatcher-5] [akka://Shutdown/user/shutdownActor1] Message [java.lang.String] from Actor[akka://Shutdown/deadLetters] to Actor[akka://Shutdown/user/shutdownActor1#-1351398790] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [06/06/2018 17:14:20.192] [Shutdown-akka.actor.default-dispatcher-5] [akka://Shutdown/user/shutdownActor2] Message [java.lang.String] from Actor[akka://Shutdown/deadLetters] to Actor[akka://Shutdown/user/shutdownActor2#-2032210650] was not delivered. [2] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
 */
