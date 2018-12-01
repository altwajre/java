import org.joda.time.DateTime

/*
Introduction

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch02s01.html
 */
object _00_Intro {
  def main(args: Array[String]): Unit = {
    println(s"Short.MinValue: ${Short.MinValue}")
    println(s"Short.MaxValue: ${Short.MaxValue}")

    println(s"Int.MinValue: ${Int.MinValue}")
    println(s"Float.MinValue: ${Float.MinValue}")

    println(s"DateTime.now: ${DateTime.now}")

  }
}
/*
Short.MinValue: -32768
Short.MaxValue: 32767
Int.MinValue: -2147483648
Float.MinValue: -3.4028235E38
DateTime.now: 2018-07-20T11:08:50.692-07:00
 */
