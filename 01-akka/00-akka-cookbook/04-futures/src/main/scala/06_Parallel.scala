import scala.concurrent.Future
import scala.util.Success
//We imported scala.concurrent.ExecutionContext.Implicits.global to execute the future.
import scala.concurrent.ExecutionContext.Implicits.global

/*
Creating a parallel app using futures

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/ea739d36-8f92-4c98-8977-bbc9bb372259.xhtml

Will learn how independent operations can be parallelize, which will reduce the running time of a program.

 */
object Fib {
  def fib(n: Int): Int = {
    def fid_tail(n: Int, a: Int, b: Int): Int = n match {
      case 0 => a
      case _ => fid_tail(n-1, b, a + b)
    }
    fid_tail(n, 0, 1)
  }
}

object Parallel extends App {
  import Fib._
  private val t1: Long = System.currentTimeMillis
  private val sum: Int = fib(100) + fib(100) + fib(100)
  println(s"sum is $sum time taken in sequential computation ${System.currentTimeMillis - t1 / 1000.0} seconds")

  private val t2: Long = System.currentTimeMillis
  private val future1 = Future(fib(100))
  private val future2 = Future(fib(100))
  private val future3 = Future(fib(100))

  val future = for {
    x <- future1
    y <- future2
    z <- future3
  } yield (x + y + z)

  future onComplete {
    case Success(sum) => {
      val endTime = (System.currentTimeMillis - t2) / 1000.0
      println(s"sum is $sum time taken in parallel computation $endTime seconds")
    }
  }

  Thread.sleep(2000)
}
/*
sum is 1354645321 time taken in sequential computation 1.527041971143445E12 seconds
sum is 1354645321 time taken in parallel computation 0.11 seconds
 */
