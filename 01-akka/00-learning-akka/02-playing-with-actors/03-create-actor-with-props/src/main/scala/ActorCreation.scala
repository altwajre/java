import MusicController.{Play, Stop}
import MusicPlayer.{StartMusic, StopMusic}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

// 1. Creating an Actor with Props

// Music Controller Messages
object MusicController {
  sealed trait ControllerMsg
  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg
  def props = Props[MusicController]
}

// Music Controller
class MusicController extends Actor {
  def receive = {
    case Play =>
      println("Music Started ...")
    case Stop =>
      println("Music Stopped ...")
  }
}

// Music Player Messages
object MusicPlayer {
  sealed trait PlayMsg
  case object StopMusic extends PlayMsg
  case object StartMusic extends PlayMsg
}

// Music Player
class MusicPlayer extends Actor {
  override def receive: Receive = {
    case StopMusic =>
      println("I don't want to stop music")
    case StartMusic =>
      // Create the 'MusicController' actor
      val controller: ActorRef = context.actorOf(MusicController.props, "controller")

      // send Play Message to controller actor
      controller ! Play
    case _ =>
      println("Unknown Message")
  }
}

object Creation extends App {
  // Create the 'creation' actor system
  private val system = ActorSystem("creation")

  // Create the 'MusicPlayer' actor
  // Props is configuration class to specify options for creation of actors
  private val player: ActorRef = system.actorOf(Props[MusicPlayer], "player")

  // send StartMusic Message to player actor
  player ! StartMusic

  // send StopMusic Message to player actor
  player ! StopMusic

  //shutdown actorsystem
  system.terminate()
}
/*
Music Started ...
I don't want to stop music
 */
