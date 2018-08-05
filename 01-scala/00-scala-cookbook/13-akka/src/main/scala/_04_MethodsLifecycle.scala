import akka.actor.{Actor, ActorSystem, Props}

/*
Understanding the Methods in the Akka Actor Lifecycle

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s05.html
 */
object _04_MethodsLifecycle {

  case object ForceRestart

  class Kenny extends Actor {
    println("entered the Kenny constructor")

    override def preStart(): Unit = {
      println("kenny: preStart")
    }

    override def postStop(): Unit = {
      println("kenny: postStop")
    }

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      println("kenny: preRestart")
      println(s"  MESSAGE: ${message.getOrElse("")}")
      println(s"  REASON: ${reason.getMessage}")
      super.preRestart(reason, message)
    }

    override def postRestart(reason: Throwable): Unit = {
      println("kenny: postRestart")
      println(s"  REASON: ${reason.getMessage}")
      super.postRestart(reason)
    }

    override def receive: Receive = {
      case ForceRestart => throw new Exception("Boom!")
      case _ => println("Kenny received a message")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("LifecycleDemo")
    val kenny = system.actorOf(Props[Kenny], name = "Kenny")

    println("# sending kenny a simple String message")
    kenny ! "hello"
    Thread.sleep(1000)

    println("# make kenny restart")
    kenny ! ForceRestart
    Thread.sleep(1000)

    println("# stopping kenny")
    system.stop(kenny)

    println("# shutting down system")
    system.terminate
  }
}
/*
entered the Kenny constructor
# sending kenny a simple String message
kenny: preStart
Kenny received a message
# make kenny restart
kenny: preRestart
  MESSAGE: ForceRestart
  REASON: Boom!
kenny: postStop
[ERROR] [08/03/2018 16:03:05.484] [LifecycleDemo-akka.actor.default-dispatcher-2] [akka://LifecycleDemo/user/Kenny] Boom!
java.lang.Exception: Boom!
	at MethodsLifecycle$Kenny$$anonfun$receive$1.applyOrElse(MethodsLifecycle.scala:37)
	at akka.actor.Actor.aroundReceive(Actor.scala:496)
	at akka.actor.Actor.aroundReceive$(Actor.scala:494)
	at MethodsLifecycle$Kenny.aroundReceive(MethodsLifecycle.scala:12)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)

entered the Kenny constructor
kenny: postRestart
  REASON: Boom!
kenny: preStart
# stopping kenny
# shutting down system
kenny: postStop
 */
