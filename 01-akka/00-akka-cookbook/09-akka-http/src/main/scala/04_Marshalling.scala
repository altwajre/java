import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.http.scaladsl.settings.ServerSettings
import akka.http.scaladsl.unmarshalling._
import com.typesafe.config.ConfigFactory

/*
Marshaling and unmarshaling data

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/130e828d-9298-4c96-a764-435ab3050e8b.xhtml

 */
case class SpeedMeasurement(timestamp: Long, latitude: Double, longitude: Double, value: Double) {
  val marshall = s"$timestamp $latitude $longitude $value"
}

object SpeedMeasurement {
  def unmarshall(str: String) = {
    str.split(" ") match {
      case Array(timestamp, latitude, longitude, value) =>
        SpeedMeasurement(timestamp.toLong, latitude.toDouble, longitude.toDouble, value.toDouble)
    }
  }
}

trait SpeedMeasurementMarshallingHelper {
  val contentType = ContentType(MediaTypes.`text/tab-separated-values`, HttpCharsets.`UTF-8`)
  implicit val utf8TextSpaceMarshaller: ToEntityMarshaller[SpeedMeasurement] =
    Marshaller.withFixedContentType(contentType) { speedMeasurement ⇒ HttpEntity(contentType, speedMeasurement.marshall) }
  implicit val utf8TextSpaceUnmarshaller: FromEntityUnmarshaller[SpeedMeasurement] =
    Unmarshaller.stringUnmarshaller.map(SpeedMeasurement.unmarshall)
}

object MarshallingServer extends HttpApp with SpeedMeasurementMarshallingHelper {
  var measurement: Option[SpeedMeasurement] = None

  val route =
    get {
      complete {
        measurement match {
          case None => StatusCodes.NotFound -> "Speed Measurement is empty"
          case Some(value) => StatusCodes.OK -> value
        }
      }
    } ~
      post {
        entity(as[SpeedMeasurement]) { speedMeasurement =>
          complete {
            measurement = Some(speedMeasurement)
            StatusCodes.OK -> s"Speed Measurement now is $speedMeasurement"
          }
        }
      }

  override protected def routes: Route = route
}

object MarshallingApplication extends App {
  MarshallingServer.startServer("0.0.0.0", 8088, ServerSettings(ConfigFactory.load))
}
/*
curl -X POST --data "140000000 40.42015 -3.70578 56.0" http://localhost:8088/
Speed Measurement now is SpeedMeasurement(140000000,40.42015,-3.70578,56.0)

curl -X GET http://localhost:8088/
140000000 40.42015 -3.70578 56.0
 */
