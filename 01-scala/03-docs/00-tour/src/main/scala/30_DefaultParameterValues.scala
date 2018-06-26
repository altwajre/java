/*
Default Parameter Values

https://docs.scala-lang.org/tour/default-parameter-values.html

Give parameters default values that can be used to allow a caller to omit those parameters
 */

case class Foo7()

object DefaultParameterValues {
  def main(args: Array[String]) = {
    log("System starting") // default parameter value is used
    log("User not found", "WARNING")
  }

  def log(message: String, level: String = "INFO") = println(s"$level: $message")
}
/*
INFO: System starting
WARNING: User not found
 */
