/*
Matching Multiple Conditions with One Case Statement

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s09.html
 */
object _08_MatchMultiConditions {
  trait Command
  case object Start extends Command
  case object Go extends Command
  case object Stop extends Command
  case object Whoa extends Command

  def executeCommand(cmd: Command) = cmd match {
    case Start | Go => println("Starting command")
    case Stop | Whoa => println("Stopping command")
  }

  def main(args: Array[String]): Unit = {
    val i = 5
    i match {
      case 1 | 3 | 5 => println("odd")
      case 2 | 4 => println("even")
    }

    val cmd = "stop"
    cmd match {
      case "start" | "go" => println("starting")
      case "stop" | "quit" | "exit" => println("stopping")
      case _ => println("doing nothing")
    }

    println("# match multi case objects")
    executeCommand(Start)
  }
}
/*
odd
stopping
# match multi case objects
Starting command
 */
