import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

/*
Creating a minimal HTTP server using Akka HTTP
https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/ebcfef81-4bc3-4b6f-bd57-2b8ebba5a571.xhtml

 */
object MinimalHttpServer extends HttpApp {
  def routes = pathPrefix("v1") {
    path("id" / Segment) { id =>
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>Hello $id from Akka Http!</h1>"))
      } ~
        post {
          entity(as[String]) { entity =>
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<b>Thanks $id for posting your message <i>$entity</i></b>"))

          }
        }
    }
  }
}

object MinimalHttpServerApplication extends App {
  MinimalHttpServer.startServer("0.0.0.0", 8088,
    ServerSettings(ConfigFactory.load))
}

/*
# Launch
Right click MinimalHttpServerApplication, click Run

# Test
curl http://localhost:8088/v1/id/ALICE
<h1>Hello ALICE from Akka Http!</h1>

curl -X POST --data 'Akka Http is Cool' http://localhost:8088/v1/id/ALICE
<b>Thanks ALICE for posting your message <i>Akka Http is Cool</i></b>
 */