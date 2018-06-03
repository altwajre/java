import akka.actor.{ActorRef, ActorSelection, ActorSystem, PoisonPill, Props}

object ActorPathApp extends App {
  private val system = ActorSystem("Actor-Paths")

  private val counter1: ActorRef = system.actorOf(Props[Counter], "Counter")

  println(s"Actor Reference for counter1: ${counter1}")

  private val counterSelection1: ActorSelection = system.actorSelection("counter")

  println(s"Actor Selection for counter1: ${counterSelection1}")

  counter1 ! PoisonPill // Must delete the existing Actor before creating new one

  Thread.sleep(1000)

  private val counter2: ActorRef = system.actorOf(Props[Counter], "Counter")

  println(s"Actor Reference for counter2: ${counter2}")

  private val counterSelection2: ActorSelection = system.actorSelection("counter")

  println(s"Actor Selection for counter2: ${counterSelection2}")

  system.terminate();

}
/*
Actor Reference for counter1: Actor[akka://Actor-Paths/user/Counter#630540360]
Actor Selection for counter1: ActorSelection[Anchor(akka://Actor-Paths/), Path(/counter)]
Actor Reference for counter2: Actor[akka://Actor-Paths/user/Counter#576056163]
Actor Selection for counter2: ActorSelection[Anchor(akka://Actor-Paths/), Path(/counter)]
 */
