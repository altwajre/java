package com.company.app

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

class MyPriorityActor extends Actor {
  override def receive: PartialFunction[Any, Unit] = {
    // Int Messages
    case x: Int => println(x)
    // String Messages
    case x: String => println(x)
    // Long Messages
    case x: Long => println(x)
    // other messages
    case x => println(x)

  }
}

// configure prio-dispatcher in application.conf
class MyPriorityActorMailbox(settings: ActorSystem.Settings, config: Config)
  extends UnboundedPriorityMailbox(
    // Create a new PriorityGenerator, lower prio means more important
    PriorityGenerator {
      // Int Messages
      case x: Int => 1
      // String Messages
      case x: String => 0
      // Long messages
      case x: Long => 2
      // other messages
      case _ => 3
    }
  )

object PriorityMailBox extends App {
  private val system = ActorSystem("PriorityMailBox")
  private val myPriorityActor: ActorRef = system.actorOf(Props[MyPriorityActor].withDispatcher("prio-dispatcher"))

  myPriorityActor ! 6.0
  myPriorityActor ! 1
  myPriorityActor ! 5.0
  myPriorityActor ! 3
  myPriorityActor ! "Hello"
  myPriorityActor ! 5
  myPriorityActor ! "I am priority actor"
  myPriorityActor ! "I process string messages first,then integer, long and others"

  system.terminate()
}
/*
Hello
I process string messages first,then integer, long and others
I am priority actor
1
3
5
6.0
5.0
 */
