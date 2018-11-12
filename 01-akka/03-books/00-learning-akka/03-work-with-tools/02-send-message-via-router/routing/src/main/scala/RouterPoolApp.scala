import Worker.Work
import akka.actor.{ActorRef, ActorSystem, Props}

object RouterPoolApp extends App {
  private val system = ActorSystem("router")
  private val router: ActorRef = system.actorOf(Props[RouterPool])

  router ! Work()
  router ! Work()
  router ! Work()

  Thread.sleep(100)

  system.terminate()
}
/*
router-akka.actor.default-dispatcher-2: I'm A Router and I received a Message...
5
router-akka.actor.default-dispatcher-2: I'm A Router and I received a Message...
5
router-akka.actor.default-dispatcher-2: I'm A Router and I received a Message...
5
router-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://router/user/$a/$b#1068361074]
router-akka.actor.default-dispatcher-3: I received Work Message and My ActorRef: Actor[akka://router/user/$a/$c#557975679]
router-akka.actor.default-dispatcher-4: I received Work Message and My ActorRef: Actor[akka://router/user/$a/$b#1068361074]
 */
