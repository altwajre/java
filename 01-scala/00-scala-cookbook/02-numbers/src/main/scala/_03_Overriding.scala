/*
Overriding the Default Numeric Type

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch02s04.html
 */
object _03_Overriding {
  def main(args: Array[String]): Unit = {
    val a1 = 1
    println(s"${a1.getClass.getSimpleName} type: $a1")
    val a2 = 1d
    println(s"${a2.getClass.getSimpleName} type: $a2")
    val a3 = 1000L
    println(s"${a3.getClass.getSimpleName} type: $a3")

    println("# annotate")
    val b1 = 1: Byte
    println(s"${b1.getClass.getSimpleName} type: $b1")
    val b2 = 1: Int
    println(s"${b2.getClass.getSimpleName} type: $b2")
    val b3 = 1: Short
    println(s"${b3.getClass.getSimpleName} type: $b3")
    val b4 = 1: Double
    println(s"${b4.getClass.getSimpleName} type: $b4")
  }
}
/*
int type: 1
double type: 1.0
long type: 1000
# annotate
byte type: 1
int type: 1
short type: 1
double type: 1.0
 */
