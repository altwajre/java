import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

class Monitor(worker: ActorRef) extends Actor {
  override def preStart(): Unit = {
    context.watch(worker)
  }

  override def postStop(): Unit = {
    println("Monitor postStop...")
  }

  override def receive: Receive = {
    case Terminated => {
      context.stop(self)
    }
  }
}

class Worker extends Actor {
  override def receive: Receive = {
    case msg => {
      println(s"Worker received ${msg}")
      context.stop(self)
    }
  }
}

object Monitoring extends App {
  // Create actor system
  private val system = ActorSystem("monitoring")

  // Create Worker Actor
  private val worker: ActorRef = system.actorOf(Props[Worker], "worker")

  // Create Monitor Actor
  private val monitor: ActorRef = system.actorOf(Props(classOf[Monitor], worker), "monitor")

  worker ! "Hi"

  system.terminate()

}
/*
Worker received Hi
Monitor postStop...
 */
