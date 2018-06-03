import Worker.Work
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RandomGroup

object RandomRouterFromCodeApp extends App {
  private val system = ActorSystem("Random-Router-From-Code")

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")

  private val paths = List("/user/w1", "/user/w2", "/user/w3")

  private val routerGroup: ActorRef = system.actorOf(RandomGroup(paths).props(), "random-router-group")

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  Thread.sleep(100)

  system.terminate()
}
/*
Random-Router-From-Code-akka.actor.default-dispatcher-5: I received Work Message and My ActorRef: Actor[akka://Random-Router-From-Code/user/w3#1172184741]
Random-Router-From-Code-akka.actor.default-dispatcher-2: I received Work Message and My ActorRef: Actor[akka://Random-Router-From-Code/user/w1#-316322438]
Random-Router-From-Code-akka.actor.default-dispatcher-6: I received Work Message and My ActorRef: Actor[akka://Random-Router-From-Code/user/w2#1345067613]
Random-Router-From-Code-akka.actor.default-dispatcher-2: I received Work Message and My ActorRef: Actor[akka://Random-Router-From-Code/user/w1#-316322438]
 */
