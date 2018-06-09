import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.routing.ConsistentHashingPool
import akka.routing.ConsistentHashingRouter.{ConsistentHashMapping, ConsistentHashable, ConsistentHashableEnvelope}
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/*
Creating a ConsistentHashingPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/6ce70fa8-7232-41b0-b9be-5d5773444219.xhtml

It always forwards a message with the same key to the same actor.
Consistent caching is used for a distributed cache across multiple nodes. It gives us flexibility regarding what is cached and where, for faster results.

How it works...

First created the message that implements the ConsistentHashable trait.
Defined an actor that maintains a map for caching.

In test app, create a ConsistentHashingPool of actors. In the output that the actor always return the same key and value which they have received for insertion in the cache.

 */
case class Evict(key: String)
case class Get(key: String) extends ConsistentHashable {
  override def consistentHashKey: Any = key
}
case class Entry(key: String, value: String)

class CacheActor extends Actor {
  var cache = Map.empty[String, String]
  override def receive: Receive = {
    case Entry(key, value) => {
      println(s"${self.path.name} adding key $key")
      cache += (key -> value)
    }
    case Get(key) => {
      println(s" ${self.path.name} fetching key $key")
      sender() ! cache.get(key).getOrElse("nothing because it is evicted")
    }
    case Evict(key) => {
      println(s" ${self.path.name} removing key $key")
      cache -= key
    }
  }
}

object ConsistantHashingpoolApp extends App {
  implicit val timeout = Timeout(10 seconds)

  private val system = ActorSystem("ConsistantHashingpool")
  def hashMapping: ConsistentHashMapping = {
    case Evict(key) => key
  }

  val cache = system.actorOf(ConsistentHashingPool(10, hashMapping = hashMapping).props(Props[CacheActor]), name = "cache")
  cache ! ConsistentHashableEnvelope(message = Entry("hello", "HELLO"), hashKey = "hello")
  cache ! ConsistentHashableEnvelope(message = Entry("hi", "HI"), hashKey = "hi")
  private val result1 = Await.result((cache ? Get("hello")).mapTo[String], 10 seconds)
  println(s"parent get child1 result: $result1")
  private val result2 = Await.result((cache ? Get("hi")).mapTo[String], 10 seconds)
  println(s"parent get child2 result: $result2")
  cache ! Evict("hi")
//  cache ! Get("hi")
  private val result3 = Await.result((cache ? Get("hi")).mapTo[String], 10 seconds)
  println(s"parent get child3 result: $result3")

  system.terminate()
}
/*
$e adding key hi
$j adding key hello
 $j fetching key hello
parent get child1 result: HELLO
 $e fetching key hi
parent get child2 result: HI
 $e removing key hi
 $e fetching key hi
parent get child3 result: nothing because it is evicted
 */
