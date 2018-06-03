import Worker.Work
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.FromConfig

/*
application.conf

akka.actor.deployment {
  /round-robin-group {
    router = round-robin-group
    routees.paths = ["/user/w1", "/user/w2", "/user/w3"]
  }
}
 */
object RoundRobinFromConfigApp extends App {
  private val system = ActorSystem("Round-Robin-From-Config")

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")

  private val routerGroup: ActorRef = system.actorOf(FromConfig.props(), "round-robin-group")

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  Thread.sleep(100)

  system.terminate()
}
/*
Round-Robin-From-Config-akka.actor.default-dispatcher-6: I received Work Message and My ActorRef: Actor[akka://Round-Robin-From-Config/user/w3#830275681]
Round-Robin-From-Config-akka.actor.default-dispatcher-7: I received Work Message and My ActorRef: Actor[akka://Round-Robin-From-Config/user/w1#-2004486231]
Round-Robin-From-Config-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://Round-Robin-From-Config/user/w2#-962112948]
Round-Robin-From-Config-akka.actor.default-dispatcher-7: I received Work Message and My ActorRef: Actor[akka://Round-Robin-From-Config/user/w1#-2004486231]
 */
