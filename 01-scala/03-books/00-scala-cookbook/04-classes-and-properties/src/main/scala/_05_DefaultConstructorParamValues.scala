/*
Providing Default Values for Constructor Parameters

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s06.html
 */
object _05_DefaultConstructorParamValues {
  class Socket(val timeout: Int = 1000)
  def main(args: Array[String]): Unit = {
    val s1 = new Socket
    println(s1.timeout)

    val s2 = new Socket(128)
    println(s2.timeout)

    val s3 = new Socket(timeout = 168)
    println(s3.timeout)

  }
}
/*
1000
128
168
 */
