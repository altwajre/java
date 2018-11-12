import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}

import scala.concurrent.duration._

// child
class Printer extends Actor {
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Printer: I am restarting because of ArithmeticException")
  }

  override def receive: Receive = {
    case msg: String => println(s"Printer $msg")
    case msg: Int => 1 / 0
  }
}

// child
class IntAdder extends Actor {
  var x = 0

  override def postStop(): Unit = {
    println("IntAdder: I am getting stopped because I got a string message")
  }

  override def receive: Receive = {
    case msg: Int => {
      x = x + msg
      println(s"IntAdder: sum is $x")
    }
    case msg: String => throw new IllegalArgumentException
  }
}

// parent
class SupervisorStrategy extends Actor {

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
    case _: ArithmeticException => {
      println("SupervisorStrategy invokes Restart")
      Restart
    }
    case _: NullPointerException => {
      println("SupervisorStrategy invokes Resume")
      Resume
    }
    case _: IllegalArgumentException => {
      println("SupervisorStrategy invokes Stop")
      Stop
    }
    case _: Exception => {
      println("SupervisorStrategy invokes Escalate")
      Escalate
    }
  }

  private val printer: ActorRef = context.actorOf(Props[Printer])
  private val intAdder: ActorRef = context.actorOf(Props[IntAdder])

  override def receive: Receive = {
    case "Start" => {
      printer ! "Hello printer"
      printer ! 10
      intAdder ! 10
      intAdder ! 10
      intAdder ! "Crash int adder"
    }
  }
}

object SupervisorStrategyApp extends App {
  private val system = ActorSystem("SupervisorStrategyApp")
  system.actorOf(Props[SupervisorStrategy]) ! "Start"

  Thread.sleep(1000)
  system.terminate()
}
/*
Printer Hello printer
IntAdder: sum is 10
IntAdder: sum is 20
SupervisorStrategy invokes Stop
SupervisorStrategy invokes Restart
Printer: I am restarting because of ArithmeticException
IntAdder: I am getting stopped because I got a string message
[ERROR] [06/07/2018 16:56:09.421] [SupervisorStrategyApp-akka.actor.default-dispatcher-3] [akka://SupervisorStrategyApp/user/$a/$b] null
java.lang.IllegalArgumentException
	at IntAdder$$anonfun$receive$2.applyOrElse(SupervisorStrategy.scala:31)
	at akka.actor.Actor.aroundReceive(Actor.scala:496)
	at akka.actor.Actor.aroundReceive$(Actor.scala:494)
	at IntAdder.aroundReceive(SupervisorStrategy.scala:19)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)

[ERROR] [06/07/2018 16:56:09.422] [SupervisorStrategyApp-akka.actor.default-dispatcher-3] [akka://SupervisorStrategyApp/user/$a/$a] / by zero
java.lang.ArithmeticException: / by zero
	at Printer$$anonfun$receive$1.applyOrElse(SupervisorStrategy.scala:14)
	at akka.actor.Actor.aroundReceive(Actor.scala:496)
	at akka.actor.Actor.aroundReceive$(Actor.scala:494)
	at Printer.aroundReceive(SupervisorStrategy.scala:7)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
 */
