package com.company.app

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.ControlMessage

// MyControlMessage will be handled on priority by ControlAwareMailbox
case object MyControlMessage extends ControlMessage

// add UnboundedControlAwareMailbox to application.conf
class Logger extends Actor {
  override def receive: Receive = {
    case MyControlMessage => println("Oh, I have to process Control message first")
    case x => println(x.toString)
  }
}

object ControlAwareMailbox extends App {
  private val system = ActorSystem("ControlAwareMailbox")
  private val actor: ActorRef = system.actorOf(Props[Logger].withDispatcher("control-aware-dispatcher"))

  actor ! "Hello"
  actor ! "how are"
  actor ! "you?"
  actor ! MyControlMessage

  system.terminate()
}
/*
Oh, I have to process Control message first
Hello
how are
you?
 */
