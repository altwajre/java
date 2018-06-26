import scala.util.Random

/*
Pattern Matching

https://docs.scala-lang.org/tour/pattern-matching.html

 */

abstract class Notification
case class Email(sender: String, title: String, body: String) extends Notification
case class SMS(caller: String, message: String) extends Notification
case class VoiceRecording(contactName: String, Link: String) extends Notification

object PatternMatching {
  def main(args: Array[String]): Unit = {
    println("# Matching on a value")
    val x = Random.nextInt(3)
    println(x match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "unknown"
    })

    println("## Match expression has a return type String")
    def matchTest(x: Int): String = x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }

    println(matchTest(3))
    println(matchTest(1))

    println("# Matching on case classes")

    def showNotification(notification: Notification): String = {
      notification match {
          // body field is ignored with _
        case Email(email, title, _) => s"Got email from $email with title: $title"
        case SMS(number, message) => s"Got SMS from $number! Message: $message"
        case VoiceRecording(name, link) => s"Received Voice Recording from $name! Link: $link"
      }
    }

    val sms = SMS("867-5309", "Are you there?")
    val voiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
    println(showNotification(sms))
    showNotification(voiceRecording)

    println("# Pattern guards")

    def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
      notification match {
        case Email(email, _, _) if importantPeopleInfo.contains(email) => s"Got email from special someone! email: $email"
        case SMS(number, _) if importantPeopleInfo.contains(number) => "Got SMS from special someone!"
        case other => showNotification(other) // nothing special, delegate to original showNotification func
      }
    }

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

    val importantEmail = Email("jenny@gmail.com", "Drinks tonight", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")

    println(showImportantNotification(sms, importantPeopleInfo))
    println(showImportantNotification(voiceRecording, importantPeopleInfo))
    println(showImportantNotification(importantEmail, importantPeopleInfo))
    println(showImportantNotification(importantSms, importantPeopleInfo))

    println("# Matching on type only")
    abstract class Device
    case class Phone(model: String) extends Device {
      def screenOff = "Turning screen off"
    }
    case class Computer(model: String) extends Device {
      def screenSaverOn = "Turning screen saver on..."
    }

    def goIdel(device: Device) = device match {
      case p: Phone => p.screenOff
      case c: Computer => c.screenSaverOn
    }

    println(goIdel(Computer("MacBookPro")))

    println("# Sealed classes")

    sealed abstract class Furniture
    case class Couch() extends Furniture
    case class Chair() extends Furniture

    def findPlaceToSit(piece: Furniture) = piece match {
      case a: Couch => "Lie on the couch"
      case b: Chair => "Site on the chair"
    }

    println(findPlaceToSit(Chair()))
  }
}
/*
# Matching on a value
zero
## Match expression has a return type String
many
one
# Matching on case classes
Got SMS from 867-5309! Message: Are you there?
# Pattern guards
Got SMS from special someone!
Received Voice Recording from Tom! Link: voicerecording.org/id/123
Got email from special someone! email: jenny@gmail.com
Got SMS from special someone!
# Matching on type only
Turning screen saver on...
# Sealed classes
Site on the chair
 */

