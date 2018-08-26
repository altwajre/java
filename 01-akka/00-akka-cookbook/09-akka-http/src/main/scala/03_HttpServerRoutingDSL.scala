import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

trait InMemoryStorageRestApi[K, V] {
  implicit val fixedPath: String

  def composedRoute(cache: mutable.Map[K, V]) =
    versionOneRoute {
      temperaturePathRoute {
        handleAllMethods(cache)
      }
    }

  private def versionOneRoute(route: Route) =
    pathPrefix("v1") {
      route
    }

  private def temperaturePathRoute(route: Route) =
    pathPrefix(fixedPath) {
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
      cache.map(keyValue => s"${keyValue._2.location}, ${keyValue._2.measurement}").mkString("\n")
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

trait PostRequestsHandler {
  def handlePost(cache: mutable.Map[String, TemperatureMeasurement]) =
    entity(as[String]) { content =>
      complete {
        content.split(",") match {
          case Array(location, _)
            if cache.contains(location) =>
            StatusCodes.Conflict -> s"$location has a value already. To update it please use PUT method."
          case Array(location, measurement) =>
            cache.put(location, TemperatureMeasurement(location, measurement.toDouble))
            s"Measurement inserted for $location"
        }
      }
    }
}

trait PutRequestsHandler {
  def handlePut(cache: mutable.Map[String, TemperatureMeasurement]) =
    path(Segment) { id =>
      entity(as[String]) { updatedMeasurement =>
        complete {
          cache.get(id) match {
            case Some(TemperatureMeasurement(location, measurement)) =>
              cache.put(id, TemperatureMeasurement(location, updatedMeasurement.toDouble))
              s"New temperature for $location is $updatedMeasurement"
            case None =>
              StatusCodes.NotFound -> s"Not temperature measurement for $id"
          }
        }
      }
    }
}

trait DeleteRequestsHandler {
  def handleDelete(cache: mutable.Map[
    String, TemperatureMeasurement]) =
    path(Segment) { id =>
      complete {
        cache.get(id) match {
          case Some(TemperatureMeasurement(location, measurement)) =>
            cache.remove(id)
            s"Removed temperature for $location"
          case None =>
            StatusCodes.NotFound -> s"Not temperature measurement for $id"
        }
      }
    }
}

class TemperatureInMemoryStorageRestApi(cache: mutable.Map[String, TemperatureMeasurement]) extends HttpApp
  with InMemoryStorageRestApi[String, TemperatureMeasurement]
  with GetRequestsHandler
  with PostRequestsHandler
  with PutRequestsHandler
  with DeleteRequestsHandler {

  implicit val fixedPath = "temperature"
  val route = composedRoute(cache)

  override protected def routes: Route = route
}

/*
main

Right click object below, click run to Launch the RestApi
 */
object TemperatureInMemoryStorageRestApiApplication extends App {
  val cache = mutable.Map.empty[String, TemperatureMeasurement]
  new TemperatureInMemoryStorageRestApi(cache).startServer("0.0.0.0", 8088, ServerSettings(ConfigFactory.load))
}

/*
curl -X POST --data "Chicago,20.0" http://localhost:8088/v1/temperature/
Measurement inserted for Chicago

curl -X POST --data "Madrid,15.0" http://localhost:8088/v1/temperature/
Measurement inserted for Madrid

curl -X GET http://localhost:8088/v1/temperature/
Madrid, 15.0
Chicago, 20.0
 */
