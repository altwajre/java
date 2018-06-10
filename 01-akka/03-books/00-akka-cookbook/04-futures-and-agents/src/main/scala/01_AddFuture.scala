import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
//We imported scala.concurrent.ExecutionContext.Implicits.global to execute the future.
import scala.concurrent.ExecutionContext.Implicits.global

case object Foo

/*
Using a future directly for a simple operation

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/3ec8814b-914d-485d-914b-401c35cabb30.xhtml

We will add two integers and use a future to run this operation asynchronously

How it works...

We computed the sum of two numbers by wrapping it in a future.
We mapped the result to Int as we were expecting sum.
We imported scala.concurrent.ExecutionContext.Implicits.global to execute the future.
We are using the Await pattern to block the current thread to retrieve the result
 */
object AddFuture extends App {
  private val future: Future[Int] = Future(1+2).mapTo[Int]
  private val sum: Int = Await.result(future, 10 seconds)
  println(s"Future Result $sum")
}
/*
Future Result 3
 */
