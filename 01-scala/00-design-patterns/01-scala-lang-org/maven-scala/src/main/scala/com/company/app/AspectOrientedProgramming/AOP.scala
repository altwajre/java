package com.company.app.AspectOrientedProgramming

trait Channel {
  def send(x: String) = println(x)
}

object LogAspect {

  trait LogAfter extends Channel {
    // after advice
    abstract override def send(x: String): Unit = {
      super.send(x); log()
    }
  }

  trait LogBefore extends Channel {
    // before advice
    abstract override def send(x: String): Unit = {
      log(); super.send(x)
    }
  }

  def log() = println("logging!")
}

object AOP {
  def main(args: Array[String]) = {
    val channel = new Channel with LogAspect.LogBefore
    channel.send("message")
  }
}

