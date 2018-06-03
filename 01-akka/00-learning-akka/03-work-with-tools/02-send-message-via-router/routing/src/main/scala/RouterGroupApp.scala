import Worker.Work
import akka.actor.{ActorRef, ActorSystem, Props}

object RouterGroupApp extends App {
  private val system = ActorSystem("RouterPool")

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")
  system.actorOf(Props[Worker], "w4")
  system.actorOf(Props[Worker], "w5")

  val workers: List[String] = List(
    "/user/w1",
    "/user/w2",
    "/user/w3",
    "/user/w4",
    "/user/w5"
  )

  private val routerGroup: ActorRef = system.actorOf(Props(classOf[RouterGroup], workers))

  routerGroup ! Work()
  routerGroup ! Work()

  Thread.sleep(100)

  system.terminate()
}
/*
I'm a Router Group and I receive Work Message...
I'm a Router Group and I receive Work Message...
RouterPool-akka.actor.default-dispatcher-8: I received Work Message and My ActorRef: Actor[akka://RouterPool/user/w2#-1005519827]
RouterPool-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://RouterPool/user/w1#999695911]
 */
