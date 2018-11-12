import Messages.{Done, GiveMeRandomNumber}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.util.Random._

object Messages {
  case class Done(randomNumber: Int)
  case object GiveMeRandomNumber
  case class Start(actorRef: ActorRef)
}

class RandomNumberGeneratorActor extends Actor {
  override def receive: Receive = {
    case GiveMeRandomNumber => {
      println("received a message to generate a random integer")
      val randomNumber = nextInt
      sender ! Done(randomNumber)
    }
  }
}

class QueryActor extends Actor {
  import Messages._
  override def receive: Receive = {
    case Start(actorRef) => {
      println("send me the next random number")
      actorRef ! GiveMeRandomNumber
    }
    case Done(randomNumber) => {
      println(s"received a random number $randomNumber")
    }
  }
}

object Communication extends App {
  import Messages._
  private val system = ActorSystem("communication")
  private val randomNumberGenerator = system.actorOf(Props[RandomNumberGeneratorActor], "randomNumberGeneratorActor")
  private val queryActor: ActorRef = system.actorOf(Props[QueryActor], "queryActor")
  queryActor ! Start(randomNumberGenerator)
  system.terminate()
}
/*
send me the next random number
received a message to generate a random integer
received a random number 1975081178
 */
