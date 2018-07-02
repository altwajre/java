package chain_of_responsibility

import scala.io.Source

/*
The chain of responsibility design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/2dc51687-c3cf-41d0-88c4-48de0ddae5fb.xhtml

The purpose of the chain of responsibility design pattern is to decouple the sender of a request from its receiver
by giving multiple objects the chance to handle the request.
 */
object ChainOfResponsibility {
  // Model
  case class Money(amount: Int)

  // the amount and the next element of the chain are to be defined by whoever extends it.
  trait Dispenser {
    val amount: Int
    val next: Option[Dispenser]

    def dispense(money: Money): Unit = {
      if(money.amount >= amount) {
        val notes = money.amount / amount
        val left = money.amount % amount
        println(s"Dispensing $notes note/s of $amount")
        if(left > 0) next.map(_.dispense(Money(left)))
      }
      else {
        next.foreach(_.dispense(money))
      }
    }
  }

  class Dispenser50(val next: Option[Dispenser]) extends Dispenser {
    override val amount: Int = 50
  }

  class Dispenser20(val next: Option[Dispenser]) extends Dispenser {
    override val amount: Int = 20
  }

  class Dispenser10(val next: Option[Dispenser]) extends Dispenser {
    override val amount: Int = 10
  }

  class Dispenser5(val next: Option[Dispenser]) extends Dispenser {
    override val amount: Int = 5
  }

  class ATM {
    // dispenser chain
    val dispenser: Dispenser = {
      val d1 = new Dispenser5(None)
      val d2 = new Dispenser10(Some(d1))
      val d3 = new Dispenser20(Some(d2))
      new Dispenser50(Some(d3))
    }

    def requestMoney(money: Money) = {
      if(money.amount % 5 != 0) {
        println("The smallest nominal is 5 and we cannot satisfy your request.")
      }
      else {
        dispenser.dispense(money)
      }
    }
  }

  def main(args: Array[String]) = {
    val atm = new ATM
    printHelp()
    Source.stdin.getLines().foreach {
      case line => {
        println(s"line=$line")
        processLine(line, atm)
      }
    }
  }

  def printHelp() = {
    println("Usage: ")
    println("1. Write an amount to withdraw...")
    println("2. Write EXIT to quit the application.")
  }

  def processLine(line: String, atm: ATM) = {
    line match {
      case "EXIT" => {
        println("Bye!")
        System.exit(0)
      }
      case l => {
        try {
          atm.requestMoney(Money(l.toInt))
          println("Thanks!")
        }
        catch {
          case _: Throwable => {
            println(s"Invalid input: $l.")
            printHelp()
          }
        }
      }
    }
  }
}
/*
Usage:
1. Write an amount to withdraw...
2. Write EXIT to quit the application.
19
line=19
The smallest nominal is 5 and we cannot satisfy your request.
Thanks!
195
line=195
Dispensing 3 note/s of 50
Dispensing 2 note/s of 20
Dispensing 1 note/s of 5
Thanks!
35
line=35
Dispensing 1 note/s of 20
Dispensing 1 note/s of 10
Dispensing 1 note/s of 5
Thanks!
15
line=15
Dispensing 1 note/s of 10
Dispensing 1 note/s of 5
Thanks!
EXIT
line=EXIT
Bye!
 */
