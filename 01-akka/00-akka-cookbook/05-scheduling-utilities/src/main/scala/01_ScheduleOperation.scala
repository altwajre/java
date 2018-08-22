import akka.actor.ActorSystem
import scala.concurrent.duration._

case object Foo

/*
Scheduling an operation at a specified interval

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/dfd397a9-4732-4e95-8be7-d05193631c56.xhtml

Want an operation to occur at a specified time or occur repeatedly after an interval. It is like a cron job.

How it works...

ScheduleOnce: schedule the operation after a specified seed time but not repeatedly.
Schedule: we have scheduled the operation every 1 second
 */
object ScheduleOperation extends App {
  private val system = ActorSystem("ScheduleOperation")
  import system.dispatcher
  system.scheduler.scheduleOnce(2 seconds){
    println(s"Sum of (1 + 2) is ${1 + 2}")
  }
  system.scheduler.schedule(3 seconds, 1 seconds) {
    println("Sorry for disturbing you every 1 second")
  }
}
/*
Sum of (1 + 2) is 3
Sorry for disturbing you every 1 second
Sorry for disturbing you every 1 second
Sorry for disturbing you every 1 second
Sorry for disturbing you every 1 second
 */
