import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory
import spray.json.DefaultJsonProtocol

import scala.util.Random._

/*
Building JSON support with Akka HTTP

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/677cd523-06e9-434a-b4b4-81519130f982.xhtml
 */
case class Item(id: Int, quantity: Int, unitPrice: Double, percentageDiscount: Option[Double])

case class Order(id: String, timestamp: Long, items: List[Item], deliveryPrice: Double, metadata: Map[String, String])

case class GrandTotal(id: String, amount: Double)

trait OrderJsonSupport extends SprayJsonSupport with
  DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat4(Item)
  implicit val orderFormat = jsonFormat5(Order)
  implicit val grandTotalFormat = jsonFormat2(GrandTotal)
}

object OrderCalculatorJsonServer extends HttpApp with OrderJsonSupport {
  val route = path("calculateGrandTotal" ~ Slash.?) {
    post {
      entity(as[Order]) {
        order =>
          complete {
            calculateGrandTotal(order)
          }
      }
    }
  } ~
    path("randomOrder") {
      get {
        complete {
          generateRandomOrder()
        }
      }
    }

  override protected def routes: Route = route

  private def calculateGrandTotal(o: Order) = {
    val amount = o.items.map(
      i => i.percentageDiscount.getOrElse(1.0d)
        * i.unitPrice * i.quantity).sum + o.deliveryPrice
    GrandTotal(o.id, amount)
  }

  private def generateRandomOrder(): Order = {
    val items = (0 to nextInt(5)).map(i => {
      Item(i, nextInt(100), 100 * nextDouble(), if (nextBoolean()) Some(nextDouble()) else None)
    }).toList
    Order(nextString(4), System.currentTimeMillis(), items, 100 * nextDouble(), Map("notes" -> "random"))
  }
}

object OrderCalculatorJsonServerApplication extends App {
  OrderCalculatorJsonServer.startServer("0.0.0.0", 8088, ServerSettings(ConfigFactory.load))
}

/*
curl -X GET http://localhost:8088/randomOrder
{
  "deliveryPrice": 58.29033968409926,
  "timestamp": 1536126716094,
  "items": [
    {
      "id": 0,
      "quantity": 70,
      "unitPrice": 53.5393654092247,
      "percentageDiscount": 0.30766367962133034
    },
    {
      "id": 1,
      "quantity": 95,
      "unitPrice": 32.28645118747606,
      "percentageDiscount": 0.1160535514288975
    }
  ],
  "id": "뒃뾢샲믊",
  "metadata": {
    "notes": "random"
  }
}

 */
