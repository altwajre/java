import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, HttpApp, Route}
import akka.http.scaladsl.settings.ServerSettings
import akka.pattern.AskTimeoutException
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import akka.pattern.ask

import scala.concurrent.duration._

/*
Exception handling

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/1910d383-cfc5-4402-bc8c-3fbd978d5bf5.xhtml
 */
trait RouteExceptionHandler {

  val routeExceptionHandler = ExceptionHandler {
    case _: ArithmeticException =>
      complete {
        StatusCodes.BadRequest -> "You values are incorrect. Probably a needs to be different from 0"
      }
    case _: AskTimeoutException =>
      complete {
        StatusCodes.ServiceUnavailable -> "Internal actor is not responding within 500 millis"
      }
  }
}

class UnresponsiveActor extends Actor {
  override def receive: Receive = Actor.ignoringBehavior
}

class HandlingExceptionsServer(someActor: ActorRef) extends HttpApp with RouteExceptionHandler {
  implicit val timeout = Timeout(500 millis)

  val route =
    handleExceptions(routeExceptionHandler) {
      path("divide") {
        parameters('a.as[Int]) { (a) =>
          complete {
            val result = 88 / a
            s"Result is: $result"
          }
        }
      } ~
        path("futureTimingOut") {
          onSuccess(someActor ? "Something") {
            case _ => complete("Actor finished processing.")
          }
        }
    }

  override protected def routes: Route = route
}

object HandlingExceptionsApplication extends App {
  val unresponsiveActor = ActorSystem().actorOf(Props[UnresponsiveActor])
  new HandlingExceptionsServer(unresponsiveActor).startServer("0.0.0.0", 8088, ServerSettings(ConfigFactory.load))
}
/*
$ curl -X GET http://localhost:8088/divide?a=2
Result is: 44

$ curl -X GET http://localhost:8088/divide?a=0
You values are incorrect. Probably a needs to be different from 0

$ curl -X GET http://localhost:8088/futureTimingOut
Internal actor is not responding within 500 millis
 */
