/*
Extending traits

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/128ee61e-e6c5-48f6-9634-3e7ce4559ecf.xhtml

Extending traits is useful in a design pattern called Stackable Traits.
 */
trait Ping {
  def ping(): Unit = {
    println("ping")
  }
}

trait Pong {
  def pong(): Unit = {
    println("pong")
  }
}

trait PingPong extends Ping with Pong {
  def pingPong(): Unit = {
    ping() // call parent trait method directly
    pong() // call parent trait method directly
    ping()
  }
}

object ExtendingTraits extends PingPong {
  def main(args: Array[String]): Unit = {
    pingPong()
  }
}
/*
ping
pong
 */
