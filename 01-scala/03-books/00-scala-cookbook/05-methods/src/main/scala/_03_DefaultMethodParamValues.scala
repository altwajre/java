/*
Setting Default Values for Method Parameters

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s04.html
 */
object _03_DefaultMethodParamValues {
  class Connection {
    def makdeConnection(timeout: Int = 5000, protocol: String = "http"): Unit = {
      println("timeout = %d, protocol = %s".format(timeout, protocol))
    }
  }

  def main(args: Array[String]): Unit = {
    val c = new Connection
    c.makdeConnection()
    c.makdeConnection(2000)
    c.makdeConnection(3000, "https")
  }
}
/*
timeout = 5000, protocol = http
timeout = 2000, protocol = http
timeout = 3000, protocol = https
 */
