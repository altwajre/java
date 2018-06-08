import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, AllForOneStrategy, OneForOneStrategy, Props}

import scala.concurrent.duration._

case class Add(a: Int, b: Int)
case class Sub(a: Int, b: Int)
case class Div(a: Int, b: Int)

// child
class Calculator(printer: ActorRef) extends Actor {
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Calculator: I am restarting because of ArithmeticException")
  }
  override def receive: Receive = {
    // send to grandchild printer
    case Add(a, b) => printer ! s"sum is ${a + b}"
    case Sub(a, b) => printer ! s"diff is ${a - b}"
    case Div(a, b) => printer ! s"div is ${a / b}"
  }
}

// grandchild
class ResultPrinter extends Actor {
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Printer: I am restarting as well")
  }

  override def receive: Receive = {
    case msg => println(msg)
  }
}

// parent
class AllForOneStrategySupervisor extends Actor {
  // AllForOneStrategy
  override val supervisorStrategy = AllForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 seconds) {
    case _: ArithmeticException => Restart
    case _: NullPointerException => Resume
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }
  private val printer: ActorRef = context.actorOf(Props[ResultPrinter])
  private val calculator: ActorRef = context.actorOf(Props(classOf[Calculator], printer))
  override def receive: Receive = {
    case "Start" => {
      // send to child calculator
      calculator ! Add(3, 5)
      calculator ! Sub(8, 3)
      calculator ! Div(5, 2)
      calculator ! Div(5, 0)
    }
  }
}

object AllForOneStrategyApp extends App {
  private val system = ActorSystem("AllForOneStrategy")
  private val supervisor: ActorRef = system.actorOf(Props[AllForOneStrategySupervisor], "supervisor")
  supervisor ! "Start"

  Thread.sleep(1000)
  system.terminate()
}
/*
sum is 8
diff is 5
div is 2
[ERROR] [06/07/2018 21:29:50.136] [AllForOneStrategy-akka.actor.default-dispatcher-2] [akka://AllForOneStrategy/user/supervisor/$b] / by zero
java.lang.ArithmeticException: / by zero
	at Calculator$$anonfun$receive$1.applyOrElse(AllForOneStrategy.scala:17)
	at akka.actor.Actor.aroundReceive(Actor.scala:496)
	at akka.actor.Actor.aroundReceive$(Actor.scala:494)
	at Calculator.aroundReceive(AllForOneStrategy.scala:10)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)

Printer: I am restarting as well
Calculator: I am restarting because of ArithmeticException
 */
