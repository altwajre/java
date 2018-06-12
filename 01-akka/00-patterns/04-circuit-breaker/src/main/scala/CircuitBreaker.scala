import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.CircuitBreaker
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._

/*

Creating a circuit breaker to avoid cascading failure

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/c69c75e5-b71c-4525-8cd0-ecf795df9afe.xhtml

In an Akka distributed system, we are expected to write a lot of services in the form of actors who do their work asynchronously.
In such an application, there are chances that some services would be under a huge load and might fail anytime.
So there would be chances that if a service depends on a particular service and if that particular service fails,
then there would be propagation of failure in the services that are calling that particular service.
Thus, we need a mechanism to avoid calling those services if they are failing or unresponsive and stop serving further requests.

To deal with such a scenario, Akka provides a circuit breaker.

 */
case class FetchRecord(recordId: Int)

case class Person(name: String, age: Int)

object DB {
  val data = Map(1 -> Person("Tom", 18),
    2 -> Person("Dick", 38),
    3 -> Person("Harry", 28),
    4 -> Person("John", 31),
    5 -> Person("Peter", 81),
    6 -> Person("Jackson", 56),
    7 -> Person("Olivia", 10),
    8 -> Person("Zoe", 7)
  )
}

class DBActor extends Actor {
  override def receive: Receive = {
    case FetchRecord(recordId) => {
      // timeout or exception will cause CircuitBreaker failures
      if(recordId >= 3 && recordId <= 5) {
        Thread.sleep(3000)
//        throw new RuntimeException("Error")
      }
      else {
        sender ! DB.data.get(recordId)
      }
    }
  }
}

object CircuitBreakerApp extends App {
  private val system = ActorSystem("CircuitBreaker")
  implicit val ec = system.dispatcher
  implicit val timeout = Timeout(3 seconds)

  val breaker = new CircuitBreaker(system.scheduler,
    maxFailures = 3,
    callTimeout = 1 seconds,
    resetTimeout = 2 seconds). // wait-time for CircuitBreaker to be fixed (close the circuit).
    onOpen(println("# State is open means something wrong #")).
    onClose(println("# State is closed means good to go #")).
    onHalfOpen(println("# State is half-open means one request is allowed #"))

  val db = system.actorOf(Props[DBActor], "DBActor")

  (1 to 10).map(recordId => {
    Thread.sleep(3000) // wait for 3 seconds because CircuitBreaker's resetTimeout is 2 seconds.
    val askFuture = breaker.withCircuitBreaker(db ? FetchRecord(recordId))
    askFuture.map(record => s"Record is: $record and RecordId $recordId").recover({
      case fail => "Failed with: " + fail.toString
    }).foreach(x => println(x))
  })
}
/*
Record is: Some(Person(Tom,18)) and RecordId 1
Record is: Some(Person(Dick,38)) and RecordId 2
Failed with: akka.pattern.CircuitBreaker$$anon$1: Circuit Breaker Timed out.
Failed with: akka.pattern.CircuitBreaker$$anon$1: Circuit Breaker Timed out.
Failed with: akka.pattern.CircuitBreaker$$anon$1: Circuit Breaker Timed out.
# State is open means something wrong #
Failed with: akka.pattern.CircuitBreakerOpenException: Circuit Breaker is open; calls are failing fast
# State is half-open means one request is allowed #
# State is closed means good to go #
Record is: Some(Person(Olivia,10)) and RecordId 7
Record is: Some(Person(Zoe,7)) and RecordId 8
Record is: None and RecordId 9
Record is: None and RecordId 10
 */
