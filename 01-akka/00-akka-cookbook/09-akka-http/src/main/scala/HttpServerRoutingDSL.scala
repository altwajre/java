import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

import scala.collection.mutable

trait InMemoryStorageRestApi[K, V] {
  implicit val fixedPath: String

  def composedRoute(cache: mutable.Map[K, V]) = versionOneRoute {
    temperaturePathRoute {
      handleAllMethods(cache)
    }
  }

  private def versionOneRoute(route: Route) = pathPrefix("v1") {
    route
  }

  private def temperaturePathRoute(route: Route) = pathPrefix(fixedPath) {
    route
  }

  private def handleAllMethods(cache: mutable.Map[K, V]) = {
    get {
      handleGet(cache)
    } ~
      post {
        handlePost(cache)
      } ~
      put {
        handlePut(cache)
      } ~
      delete {
        handleDelete(cache)
      }
  }

  def handleGet(cache: mutable.Map[K, V]): Route

  def handlePut(cache: mutable.Map[K, V]): Route

  def handlePost(cache: mutable.Map[K, V]): Route

  def handleDelete(cache: mutable.Map[K, V]): Route
}

case class TemperatureMeasurement(location: String, measurement: Double)

trait GetRequestsHandler {
  def handleGet(cache: mutable.Map[String, TemperatureMeasurement]) = pathEndOrSingleSlash {
    complete {
      cache.map(keyValue => s"${keyValue._2.location}, ${keyValue._2.measurement}").mkString("n")
    }
  } ~
  path(Segment) { id =>
    complete {
      cache.get(id) match {
        case Some(TemperatureMeasurement(location, measurement)) => s"Temperature for $location is $measurement"
        case None => StatusCodes.NotFound -> s"Not temperature measurement for $id"
      }
    }
  }
}

