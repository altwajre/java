import akka.util.Timeout

import scala.collection.immutable
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
//We imported scala.concurrent.ExecutionContext.Implicits.global to execute the future.
import scala.concurrent.ExecutionContext.Implicits.global

case object Foo4

/*
Reducing a sequence of futures

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/06fd0e45-1c1a-47e7-9b53-0f0d18636208.xhtml

compose a sequence of futures of the same type.

How it works...

Created futures of numbers from 1 to 10 and obtained a list of futures;
Reduced the futures using the Future.reduce method which takes a sequence of futures and a function to reduce them all.
 */
object RedcuingFutures extends App {
  private val timeout = Timeout(10 seconds)
  private val listOfFutures: immutable.IndexedSeq[Future[Int]] = (1 to 10).map(Future(_))
  private val finalFuture: Future[Int] = Future.reduceLeft(listOfFutures)(_ + _)
  println(s"sum of numbers from 1 to 10 is ${Await.result(finalFuture, 10 seconds)}")
}
/*
sum of numbers from 1 to 10 is 55
 */
