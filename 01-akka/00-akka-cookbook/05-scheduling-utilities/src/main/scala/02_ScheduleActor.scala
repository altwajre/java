import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import scala.concurrent.duration._

case object Foo2

/*
Scheduling an actor's operation at a specified interval

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/18234773-848e-498a-ad7a-c3a56f3f098b.xhtml

Scheduling an actor is the same as we scheduling the simple operation.

 */
class RandomIntAdder extends Actor {
  val r = scala.util.Random
  override def receive: Receive = {
    case "tick" => {
      val randomInta = r.nextInt(10)
      val randomIntb = r.nextInt(10)
      println(s"sum of $randomInta and $randomIntb is ${randomInta + randomIntb}")
    }
  }
}

object ScheduleActor extends App {
  val system = ActorSystem("ScheduleActor")
  import system.dispatcher
  private val actor: ActorRef = system.actorOf(Props[RandomIntAdder])
  system.scheduler.scheduleOnce(2 seconds, actor, "tick")
  system.scheduler.schedule(3 seconds, 2 seconds, actor, "tick")
}
/*
sum of 2 and 1 is 3
sum of 5 and 5 is 10
sum of 0 and 1 is 1
sum of 3 and 4 is 7
sum of 7 and 9 is 16
 */
