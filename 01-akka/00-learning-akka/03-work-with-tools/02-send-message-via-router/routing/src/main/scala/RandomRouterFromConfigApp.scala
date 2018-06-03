import Worker.Work
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.FromConfig

/*
application.conf

akka.actor.deployment {
  /random-router-pool {
    router = random-pool
    nr-of-instances = 3
  }
}
 */
object RandomRouterFromConfigApp extends App {
  private val system = ActorSystem("Random-Router")

  private val routerPool: ActorRef = system.actorOf(FromConfig.props(Props[Worker]), "random-router-pool")

  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()

  Thread.sleep(100)

  system.terminate()
}
/*
Random-Router-akka.actor.default-dispatcher-2: I received Work Message and My ActorRef: Actor[akka://Random-Router/user/random-router-pool/$a#-20981971]
Random-Router-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://Random-Router/user/random-router-pool/$c#1516858878]
Random-Router-akka.actor.default-dispatcher-2: I received Work Message and My ActorRef: Actor[akka://Random-Router/user/random-router-pool/$a#-20981971]
Random-Router-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://Random-Router/user/random-router-pool/$c#1516858878]
 */
