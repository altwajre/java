import Worker.Work
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool

object RoundRobinFromCodeApp extends App {
  private val system = ActorSystem("Round-Robin-Router")

  private val routerPool: ActorRef = system.actorOf(RoundRobinPool(3).props(Props[Worker]), "round-robin-pool")

  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()

  Thread.sleep(100)

  system.terminate()
}
/*
Round-Robin-Router-akka.actor.default-dispatcher-3: I received Work Message and My ActorRef: Actor[akka://Round-Robin-Router/user/round-robin-pool/$b#503062461]
Round-Robin-Router-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://Round-Robin-Router/user/round-robin-pool/$c#-1641111559]
Round-Robin-Router-akka.actor.default-dispatcher-2: I received Work Message and My ActorRef: Actor[akka://Round-Robin-Router/user/round-robin-pool/$a#-1310951542]
Round-Robin-Router-akka.actor.default-dispatcher-2: I received Work Message and My ActorRef: Actor[akka://Round-Robin-Router/user/round-robin-pool/$a#-1310951542]
 */
