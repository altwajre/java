import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

/*
Logging with actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/72b44ad4-f91b-469a-91ba-9ff0b83dc974.xhtml

Logging is the best way to debug an application for errors and information.
In Akka, logging is not tied to a particular API, but we will go with ActorLogging provided by Akka.

https://doc.akka.io/docs/akka/2.4.14/scala/logging.html#logging-of-dead-letters

 */
class LoggingActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case(a: Int, b: Int) => {
      log.info(s"sum of $a and $b is ${a + b}")
    }
    case msg => log.warning(s"Unknown msg: $msg")
  }
}

object Logging extends App {
  private val system = ActorSystem("Logging")
  private val actor: ActorRef = system.actorOf(Props[LoggingActor], "SumActor")
  actor ! (2, 3)
  actor ! "stranger"
  system.terminate()
}
/*
[INFO] [06/11/2018 18:19:32.603] [Logging-akka.actor.default-dispatcher-2] [akka://Logging/user/SumActor] sum of 2 and 3 is 5
[WARN] [06/11/2018 18:19:32.604] [Logging-akka.actor.default-dispatcher-2] [akka://Logging/user/SumActor] Unknown msg: stranger
 */
