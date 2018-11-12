import akka.actor.{ActorRef, ActorSystem, Props}

object WatcherApp extends App {
  private val system = ActorSystem("Watsh-actor-selection")
  private val counter: ActorRef = system.actorOf(Props[Counter], "counter")
  private val watcher: ActorRef = system.actorOf(Props[Watcher], "watcher")

  Thread.sleep(1000)

  system.terminate()
}
/*
Actor Reference for counter is Actor[akka://Watsh-actor-selection/user/counter#715796010]
 */
