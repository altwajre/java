import java.util.concurrent.TimeUnit

import akka.http.scaladsl.server.{Directive0, HttpApp, Route}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.settings.ServerSettings
import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
import com.typesafe.config.ConfigFactory

/*
Understanding directives

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/b73d863c-5fd8-473f-bbeb-17b2b440b22a.xhtml

Use Dropwizard metrics to measure the number of times a particular path of a route is called
and how long it took to process it.
 */
trait MetricDirectives {
  def meter(metricRegistry: MetricRegistry): Directive0 = {
    extractMethod.flatMap[Unit] {
      httpMethod =>
        extractUri.flatMap {
          uri =>
            metricRegistry.meter(s"meter-Method[${httpMethod.value}]-Uri[${uri.path.toString}]").mark
            pass
        }
    }
  }

  def timer(metricRegistry: MetricRegistry): Directive0 = {
    extractMethod.flatMap[Unit] {
      httpMethod =>
        extractUri.flatMap {
          uri =>
            val timer = metricRegistry.timer(s"timer-Method[${httpMethod.value}]-Uri[${uri.path.toString}]")
            val timerContext = timer.time()
            mapRouteResult {
              x =>
                timerContext.stop()
                x
            }
        }
    }
  }
}

object CustomDirectiveServer extends HttpApp with MetricDirectives {
  private val metricRegistry = new MetricRegistry()
  ConsoleReporter.forRegistry(metricRegistry)
    .build()
    .start(10, TimeUnit.SECONDS)

  val route = timer(metricRegistry) {
    get {
      complete {
        Thread.sleep(200); "Hello from GET!"
      }
    } ~
      post {
        complete {
          Thread.sleep(500); "Hello from POST!"
        }
      } ~
      put {
        meter(metricRegistry) {
          complete {
            "Hello from PUT!"
          }
        }
      }
  }

  override protected def routes: Route = route
}

object CustomDirectivesApplication extends App {
  CustomDirectiveServer.startServer("0.0.0.0", 8088, ServerSettings(ConfigFactory.load))
}
/*
$ curl -X POST http://localhost:8088
Hello from POST!
$ curl -X POST http://localhost:8088
Hello from POST!
$ curl -X POST http://localhost:8088
Hello from POST!
$ curl -X GET http://localhost:8088
Hello from GET!
$ curl -X GET http://localhost:8088
Hello from GET!
$ curl -X PUT http://localhost:8088
Hello from PUT!
 */
