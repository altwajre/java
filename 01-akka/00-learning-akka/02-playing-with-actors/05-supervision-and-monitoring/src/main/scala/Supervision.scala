import Child.{RestartException, ResumeException, StopException}
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}

import scala.concurrent.duration._

object Child {

  case object ResumeException extends Exception

  case object StopException extends Exception

  case object RestartException extends Exception

}

// Aphrodite
class Child extends Actor {
  override def preStart(): Unit = {
    println("Child preStart hook...")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Child preRestart hook...")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    println("Child postRestart hook...")
    super.postRestart(reason)
  }

  override def postStop(): Unit = {
    println("Child postStop...")
  }

  override def receive: Receive = {
    case "Resume" =>
      throw ResumeException
    case "Stop" =>
      println("Child.Receive Stop...")
      throw StopException
    case "Restart" =>
      throw RestartException
    case _ =>
      throw new Exception
  }
}

// Hera
class Parent extends Actor {

  import Child._

  var childRef: ActorRef = _

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
    case ResumeException => Resume
    case RestartException => Restart
    case StopException => Stop
    case _: Exception => Escalate
  }

  override def preStart(): Unit = {
    // Create Child Actor
    childRef = context.actorOf(Props[Child], "Child")
    Thread.sleep(100)
  }

  override def receive: Receive = {
    case msg =>
      println(s"Parent received ${msg}")
      childRef ! msg
      Thread.sleep(100)
  }
}

object Supervision extends App {
  // Create the 'supervision' actor system
  private val system = ActorSystem("supervision")

  // Create Parent Actor
  private val parent: ActorRef = system.actorOf(Props[Parent], "parent")

  // send "Stop" message to parent Actor
  parent ! "Stop"
  Thread.sleep(1000)
  println("")

  system.terminate()

}
/*
Child preStart hook...
Parent received Stop
Child.Receive Stop...
Child postStop...
[ERROR] [05/31/2018 17:51:14.077] [supervision-akka.actor.default-dispatcher-4] [akka://supervision/user/parent/Child] null
Child$StopException$
	at Child$StopException$.<clinit>(Supervision.scala)
	at Child$$anonfun$receive$1.applyOrElse(Supervision.scala:42)
	at akka.actor.Actor.aroundReceive(Actor.scala:496)
	at akka.actor.Actor.aroundReceive$(Actor.scala:494)
	at Child.aroundReceive(Supervision.scala:18)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
 */
