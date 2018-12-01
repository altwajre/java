import net.liftweb.json.DefaultFormats
import net.liftweb.json._

/*
Parsing JSON Data into an Array of Objects

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch15s05.html
 */
object _04_CreateObjectArrayFromJsonString {
  case class EmailAccount(
                           accountName: String,
                           url: String,
                           username: String,
                           password: String,
                           minutesBetweenChecks: Int,
                           usersOfInterest: List[String]
                         )

  def main(args: Array[String]): Unit = {
    implicit val formats = DefaultFormats

    // a JSON string that represents a list of EmailAccount instance
    val jsonString ="""
{
  "accounts": [
  { "emailAccount": {
    "accountName": "YMail",
    "username": "USERNAME",
    "password": "PASSWORD",
    "url": "imap.yahoo.com",
    "minutesBetweenChecks": 1,
    "usersOfInterest": ["barney", "betty", "wilma"]
  }},
  { "emailAccount": {
    "accountName": "Gmail",
    "username": "USER",
    "password": "PASS",
    "url": "imap.gmail.com",
    "minutesBetweenChecks": 1,
    "usersOfInterest": ["pebbles", "bam-bam"]
  }}
  ]
}
"""

    // json is a JValue instance
    val json = parse(jsonString)

    val elements = (json \\ "emailAccount").children
    for(acct <- elements) {
      val m = acct.extract[EmailAccount]
      println(s"Account: ${m.url}, ${m.username}, ${m.password}")
      println(s"  Users: ${m.usersOfInterest.mkString(",")}")
    }
  }
}
/*
Account: imap.yahoo.com, USERNAME, PASSWORD
  Users: barney,betty,wilma
Account: imap.gmail.com, USER, PASS
  Users: pebbles,bam-bam
 */
