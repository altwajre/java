import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class Worker extends Actor {
  override def receive: Receive = {
    case "Start" => {
      println("Worker start ...")
    }
    case "Stop" => {
      println("Worker stop ...")
    }
  }
}

object CaseStringActor extends App {
  // Create the actor system
  private val system = ActorSystem("CaseStringActor")

  // Create actor
  private val worker: ActorRef = system.actorOf(Props[Worker], "worker")

  // send "Start" message to Worker Actor
  worker ! "Start"

  system.terminate()
}
