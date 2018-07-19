package chain_of_responsibility

import scala.io.Source

/*
The chain of responsibility design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/7a28bcef-4256-4ca8-aa72-9f5f210b7686.xhtml

use partial functions to achieve the chain of responsibility
 */
object ScalaChainOfResponsibility {

  // Model
  case class Money(amount: Int)

  trait PartialFunctionDispenser {
    def dispense(dispenserAmount: Int): PartialFunction[Money, Money] = {
      case Money(amount) if amount >= dispenserAmount => {
        val notes = amount / dispenserAmount
        val left = amount % dispenserAmount
        println(s"Dispensing $notes note/s of $dispenserAmount.")
        Money(left)
      }
      case m @ Money(amount) => m
    }
  }

  class PartialFunctionATM extends PartialFunctionDispenser {
    val dispenser = dispense(50)
      .andThen(dispense(20))
      .andThen(dispense(10))
      .andThen(dispense(5))

    def requestMoney(money: Money): Unit = {
      if (money.amount % 5 != 0) {
        println("The smallest nominal is 5 and we cannot satisfy your request.")
      }
      else {
        dispenser(money)
      }
    }
  }

  def main(args: Array[String]) = {
    val atm = new PartialFunctionATM
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

  def processLine(line: String, atm: PartialFunctionATM) = {
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
Dispensing 3 note/s of 50.
Dispensing 2 note/s of 20.
Dispensing 1 note/s of 5.
Thanks!
35
line=35
Dispensing 1 note/s of 20.
Dispensing 1 note/s of 10.
Dispensing 1 note/s of 5.
Thanks!
15
line=15
Dispensing 1 note/s of 10.
Dispensing 1 note/s of 5.
Thanks!
EXIT
line=EXIT
Bye!
 */
