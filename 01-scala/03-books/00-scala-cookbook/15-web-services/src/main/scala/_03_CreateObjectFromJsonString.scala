import net.liftweb.json._
import net.liftweb.json.DefaultFormats

/*
Creating a Simple Scala Object from a JSON String

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch15s04.html
 */
object _03_CreateObjectFromJsonString {

  case class MailServer(url: String, username: String, password: String)

  def main(args: Array[String]): Unit = {
    implicit val formats = DefaultFormats

    // simulate a json string
    val jsonString =
      """
  {
    "url": "imap.yahoo.com",
    "username": "myusername",
    "password": "mypassword"
  }
  """

    // convert a String to a JValue object
    val jValue = parse(jsonString)

    // create a MailServer object from the string
    val mailServer = jValue.extract[MailServer]
    println(mailServer.url)
    println(mailServer.username)
    println(mailServer.password)
  }
}
/*
imap.yahoo.com
myusername
mypassword
 */
