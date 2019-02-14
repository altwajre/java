import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.util.{Failure, Success}

// https://doc.akka.io/docs/akka-http/current/client-side/request-level.html

object HttpClient {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://localhost:8080/api/whiskies"))

    responseFuture
      .onComplete {
        case Success(res) => {
          //          println("# Response: " + res)
          println("# Response.entity: "+res.entity.toString)

          //          system.terminate()
        }
        case Failure(_) => sys.error("something wrong")
      }
  }
}
/*
# Response.entity: HttpEntity.Strict(application/json,[ {
  "id" : 0,
  "name" : "Bowmore 15 Years Laimrig",
  "origin" : "Scotland, Islay",
  "state" : "ACTIVE"
}, {
  "id" : 1,
  "name" : "Talisker 57° North",
  "origin" : "Scotland, Island",
  "state" : "ACTIVE"
} ])
 */
