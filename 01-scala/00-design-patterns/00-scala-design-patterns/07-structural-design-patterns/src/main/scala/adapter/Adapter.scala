package adapter
/*
The adapter design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/b064443d-6b13-4a48-a2fc-206438cefad0.xhtml

When using a third party logging library class Logger{},
we extend it class AppLogger extends Logger with Log {} to have info, debug, warning, error methods that only take message.
 */
object Adapter {
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
  // adapter
  class AppLogger extends Logger with Log {
    override def info(message: String): Unit = log(message, "info")

    override def debug(message: String): Unit = log(message, "warning")

    override def warning(message: String): Unit = log(message, "error")

    override def error(message: String): Unit = log(message, "debug")
  }

  def main(args: Array[String]) = {
    val logger = new AppLogger
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.error("Show an error message.")
    logger.warning("About to finish.")
    logger.info("Bye!")
  }
}
/*
INFO: This is an info message.
WARNING: Debug something here.
DEBUG: Show an error message.
ERROR: About to finish.
INFO: Bye!
 */
