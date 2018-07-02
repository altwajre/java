package command

import scala.collection.mutable.ListBuffer

/*
The command design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/310507b3-dfb7-4f29-8152-e94db0250935.xhtml

- Command: the interface and its implementations that are being called by the invoker
- Receiver: the object knows how commands are executed.
- Invoker: it invokes the commands by calling their interface method.
 */
object Command {
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
  class RobotController {
    val history = ListBuffer[RobotCommand]()

    def issueCommand(command: RobotCommand) = {
      command +=: history
      command.execute()
    }

    def showHistory() = {
      history.foreach(println)
    }
  }

  def main(args: Array[String]): Unit = {
    val robot = Robot()
    val robotController = new RobotController
    robotController.issueCommand(MakeSandwichCommand(robot))
    robotController.issueCommand(PourJuiceCommand(robot))
    println("# Eating and having some juice")
    robotController.issueCommand(CleanUpCommand(robot))
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
CleanUpCommand(Robot())
PourJuiceCommand(Robot())
MakeSandwichCommand(Robot())
 */
