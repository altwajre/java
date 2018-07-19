package command

import scala.collection.mutable.ListBuffer

/*
The command design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/eeecf60d-d389-40d8-89a7-3e6a6f60bb78.xhtml

by-name parameters that is replaceable with passing functions as parameters.

The by-name parameters method is useful when we don't want to write extra code for command interface and implementations.
 */
object ScalaCommand {
  // Receiver
  case class Robot() {
    def makeSandwich() = println("Making a sandwich.")
    def pourJuice() = println("Pouring juice.")
    def cleanUp() = println("Cleaning up.")
  }

  // Commands
  trait RobotCommand {
    def execute(): Unit
  }

  case class MakeSandwichCommand(robot: Robot) extends RobotCommand {
    override def execute(): Unit = robot.makeSandwich()
  }

  case class PourJuiceCommand(robot: Robot) extends RobotCommand {
    override def execute(): Unit = robot.pourJuice()
  }

  case class CleanUpCommand(robot: Robot) extends RobotCommand {
    override def execute(): Unit = robot.cleanUp()
  }

  // Invoker
  class RobotByNameController {
    val history = ListBuffer[() => Unit]()

    def issueCommand(command: => Unit) = {
      command _ +=: history
      command
    }

    def showHistory() = {
      history.foreach(println)
    }
  }

  def main(args: Array[String]) = {
    val robot = Robot()
    val robotController = new RobotByNameController
    robotController.issueCommand(MakeSandwichCommand(robot).execute())
    robotController.issueCommand(PourJuiceCommand(robot).execute())
    println("# Eating and having some juice")
    robotController.issueCommand(CleanUpCommand(robot).execute())
    println("# Show History:")
    robotController.showHistory()
  }
}
/*
Making a sandwich.
Pouring juice.
# Eating and having some juice
Cleaning up.
# Show History:
command.ScalaCommand$$$Lambda$7/142666848@36d64342
command.ScalaCommand$$$Lambda$6/191382150@340f438e
command.ScalaCommand$$$Lambda$1/2111991224@30c7da1e
 */
