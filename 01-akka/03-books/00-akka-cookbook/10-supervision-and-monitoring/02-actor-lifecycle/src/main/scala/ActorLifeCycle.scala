import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent._
import scala.concurrent.duration._

case object Error
case class StopActor(actorRef: ActorRef)

class LifeCycleActor extends Actor {
  var sum = 1

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(s"sum in preRestart is $sum")
  }

  override def preStart(): Unit = {
    println(s"sum in preStart is $sum")
  }

  override def postRestart(reason: Throwable): Unit = {
    sum = sum * 2
    println(s"sum in postRestart is $sum")
  }

  override def postStop(): Unit = {
    println(s"sum in postStop is ${sum * 3}")
  }

  override def receive: Receive = {
    case Error => throw new ArithmeticException()
    case _ => println("default msg")
  }
}

class Supervisor extends Actor {
  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
    // the supervisor handles the failure by restarting the actor
    case _: ArithmeticException => Restart
    case t => super.supervisorStrategy.decider.applyOrElse(t, (_: Any) => Escalate)
  }

  override def receive: Receive = {
    case (props: Props, name: String) => {
      println(s"Supervisor.receive $name")
      sender ! context.actorOf(props, name)
    }
    case StopActor(actorRef) => {
      println(s"Supervisor.receive StopActor")
      context.stop(actorRef)
    }
  }
}

object ActorLifeCycle extends App {
  implicit val timeout = Timeout(2 seconds)
  private val system = ActorSystem("ActorLifeCycle")
  private val supervisor: ActorRef = system.actorOf(Props[Supervisor], "supervisor")
  private val childFuture = supervisor ? (Props(new LifeCycleActor), "LifeCycleActor")
  private val child: ActorRef = Await.result(childFuture.mapTo[ActorRef], 2 seconds)
  child ! Error
  Thread.sleep(1000)
  supervisor ! StopActor(child)
  system.terminate()
}
/*
Supervisor.receive LifeCycleActor
sum in preStart is 1
sum in preRestart is 1
sum in postRestart is 2
[ERROR] [06/07/2018 10:27:29.297] [ActorLifeCycle-akka.actor.default-dispatcher-6] [akka://ActorLifeCycle/user/supervisor/LifeCycleActor] null
java.lang.ArithmeticException
	at LifeCycleActor$$anonfun$receive$1.applyOrElse(ActorLifeCycle.scala:33)
	at akka.actor.Actor.aroundReceive(Actor.scala:496)
	at akka.actor.Actor.aroundReceive$(Actor.scala:494)
	at LifeCycleActor.aroundReceive(ActorLifeCycle.scala:12)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)

Supervisor.receive StopActor
sum in postStop is 6
 */
