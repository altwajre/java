import akka.actor.Actor

object Counter {
  final case class Inc(num: Int)
  final case class Dec(num: Int)
}

class Counter extends Actor {

  import Counter._

  var count = 0

  override def receive: Receive = {
    case Inc(x) =>
      count += x
    case Dec(x) =>
      count -= x
  }
}
