/*
Simple Concurrency with Futures

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s10.html

Run tasks concurrently, and handle their results when the tasks finish
 */

/*
RUN ONE TASK, BUT BLOCK

- The import statements bring the code into scope that's needed.
- The ExecutionContext.Implicits.global import statement imports the "default global execution context".
An execution context as being a thread pool, and this is a simple way to get access to a thread pool.
- Create a future, and pass it into a block of code to run. The code will be executed at some point in the future.
- Await.result method will wait for up to one second for the Future to return.
If the Future doesn't return within 1 second, it throws java.util.concurrent.TimeoutException.
 */
// 1 - the imports
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

object Blocking {
  // used by 'time' method
  implicit val baseTime = System.currentTimeMillis

  def main(args: Array[String]): Unit = {
    // 2 - create a Future
    val future = Future {
      Thread.sleep(500)
      18
    }

    // 3 - this is blocking (blocking is bad)
    val result = Await.result(future, 1 second)
    println(result)

    Thread.sleep(1000)
  }
}
/*
2
 */

/*
RUN ONE THING, BUT DON’T BLOCK—USE CALLBACK

- The future.onComplete method call sets up the callback. When the Future completes, it makes a callback to onComplete.
- The Future will either return the desired result (28), or an exception.
 */
object NonBlockingOnComplete {
  def main(args: Array[String]): Unit = {
    println("starting calculation...")
    val future = Future {
      Thread.sleep(Random.nextInt(500))
      28
    }

    println("before onComplete")
    future.onComplete {
      case Success(value) => println(s"Future onComplete is called. value=$value")
      case Failure(e) => e.printStackTrace
    }

    // do the rest of the work
    println("do work A...")
    Thread.sleep(100)
    println("do work B...")
    Thread.sleep(100)
    println("do work C...")
    Thread.sleep(100)

    Thread.sleep(2000)
  }
}

/*
starting calculation...
before onComplete
do work A...
do work B...
do work C...
future onComplete is called. value = 28
 */

/*
CREATING A METHOD TO RETURN A FUTURE[T]
 */
object MethodReturnsFuture {
  implicit val baseTime = System.currentTimeMillis

  def longRunningComputation(i: Int): Future[Int] = Future {
    Thread.sleep(100)
    i + 1
  }

  def main(args: Array[String]): Unit = {
    // this does not block
    longRunningComputation(27).onComplete {
      case Success(value) => println(s"Future onComplete is called. value=$value")
      case Failure(e) => e.printStackTrace
    }

    // keep the jvm from shutting down
    Thread.sleep(1000)
  }
}

/*
Future onComplete is called. value=28
 */

object RunningMultipleFutures {
  def main(args: Array[String]): Unit = {
    println("starting futures")
    val future1 = Future {
      Thread.sleep(Random.nextInt(500))
      18
    }
    val future2 = Future {
      Thread.sleep(Random.nextInt(500))
      28
    }
    val future3 = Future {
      Thread.sleep(Random.nextInt(500))
      38
    }

    println("before for-comprehesion")
    val result = for {
      f1 <- future1
      f2 <- future2
      f3 <- future3
    } yield (f1 + f2 + f3)

    println("before onSuccess")
    result onComplete {
      case Success(value) => println(s"Futures are completed. total=${result.value.get.get}")
    }

    println("before sleep at the end")
    // keep the jvm alive
    Thread.sleep(2000)
  }
}
/*
starting futures
before for-comprehesion
before onSuccess
before sleep at the end
Futures are completed. total=84
 */
