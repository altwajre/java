import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
//We imported scala.concurrent.ExecutionContext.Implicits.global to execute the future.
import scala.concurrent.ExecutionContext.Implicits.global

case object Foo

/*
Using for-comprehensions for futures

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/cd21f974-11ae-4473-a06f-c8c5881037a6.xhtml

Composing futures, iterate over futures as we do with Scala collections.

How it works...
We are creating two futures that add integers, and we are using for-comprehension to iterate over both futures.
We are not blocking on individual futures. Instead, we are creating a final future and waiting (blocking) on it for a result.
 */
object ForComprehensions extends App {
  private val futureA = Future(1 + 2)
  private val futureB = Future(10 + 20)
  private val finalFuture: Future[Int] = for {
    a <- futureA
    b <- futureB
  } yield a + b

  println("Future result is " + Await.result(finalFuture, 1 seconds))

}
/*
Future result is 33
 */
