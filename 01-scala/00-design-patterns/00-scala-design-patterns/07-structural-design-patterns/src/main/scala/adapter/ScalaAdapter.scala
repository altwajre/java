package adapter
/*
The adapter design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/c58516e4-ba22-4c74-811f-37445063144c.xhtml

 */
object ScalaAdapter {

  // third party library - external code that cannot edit
  class Logger {
    def log(message: String, severity: String) = {
      println(s"${severity.toUpperCase}: $message")
    }
  }

  // own company code
  trait Log {
    def info(message: String)
    def debug(message: String)
    def warning(message: String)
    def error(message: String)
  }

  // Use implicit conversion to DI methods to the third party class Logger
  // It will automatically convert a Logger instance to the implicit class AppLogger
  implicit class AppLogger(logger: Logger) extends Log {
    override def info(message: String): Unit = logger.log(message, "info")

    override def debug(message: String): Unit = logger.log(message, "debug")

    override def warning(message: String): Unit = logger.log(message, "warning")

    override def error(message: String): Unit = logger.log(message, "error")
  }

  def main(args: Array[String]) = {
    val logger = new Logger
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.error("Show an error message.")
    logger.warning("About to finish.")
    logger.info("Bye!")
  }
}
/*
INFO: This is an info message.
DEBUG: Debug something here.
ERROR: Show an error message.
WARNING: About to finish.
INFO: Bye!
 */
