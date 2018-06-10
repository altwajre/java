import scala.concurrent.Future
import scala.util.{Failure, Success}
//We imported scala.concurrent.ExecutionContext.Implicits.global to execute the future.
import scala.concurrent.ExecutionContext.Implicits.global

case object Foo

/*
Handling callback on futures

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/2d128ff8-0dab-427b-a967-829f017f1c00.xhtml

How to handle future responses with callback functions; it is also known as asynchronous handling of futures.
Using the callback function, we don't block the current thread for response from a future.

How it works...

We used the callback function, onComplete.
In the onComplete function, we handled two cases, which could either be Success or Failure.
It handled the future asynchronously.

 */
object Callback extends App {
  private val future: Future[Int] = Future(1 + 2).mapTo[Int]
  future onComplete {
    case Success(result) => println(s"callback result is $result")
    case Failure(fail) => fail.printStackTrace()
  }
  println("Executed before callback")
}
/*
callback result is 3
Executed before callback
 */
