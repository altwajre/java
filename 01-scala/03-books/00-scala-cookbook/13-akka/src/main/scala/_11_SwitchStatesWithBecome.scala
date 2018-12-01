import akka.actor.{Actor, ActorSystem, Props}

/*
Switching Between Different States with become

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s12.html
 */
object _11_SwitchStatesWithBecome {
  case object ActNormalMessage
  case object TryToFindSolution
  case object BadGuysMakeMeAngry

  class DavidActor extends Actor {
    import context._

    def angryState: Receive = {
      case ActNormalMessage => {
        println("I'm, back to being David")
        become(normalState)
      }
    }

    def normalState: Receive = {
      case TryToFindSolution =>
        println("Looking for solution to my problem...")
      case BadGuysMakeMeAngry =>
        println("I'm getting angry...")
        become(angryState)
    }

    def receive = {
      case BadGuysMakeMeAngry => become(angryState)
      case ActNormalMessage => become(normalState)
    }
  }
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("SwitchStatesWithBecome")
    val actor = system.actorOf(Props[DavidActor], name = "actor")
    actor ! ActNormalMessage // init to normalState
    actor ! TryToFindSolution
    actor ! BadGuysMakeMeAngry
    Thread.sleep(1000)
    actor ! ActNormalMessage

    system.terminate
  }
}
/*
Looking for solution to my problem...
I'm getting angry...
I'm, back to being David
 */
