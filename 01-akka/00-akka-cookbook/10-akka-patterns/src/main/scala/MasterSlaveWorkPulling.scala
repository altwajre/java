import akka.actor.{Actor, ActorLogging}

object MasterSlaveWorkPulling {
  case object JoinWorker
  case object DeregisterWorker
}

class MasterSlaveWorkPulling(maxQueueSize: Int) extends Actor with ActorLogging {
  override def receive: Receive = ???
}
