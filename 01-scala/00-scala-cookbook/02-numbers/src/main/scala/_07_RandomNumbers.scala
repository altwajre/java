import scala.util.Random

/*
Generating Random Numbers

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch02s08.html
 */
object _07_RandomNumbers {
  def main(args: Array[String]): Unit = {
    val r = Random
    val i1 = r.nextInt
    println(s"${i1.getClass.getSimpleName}: ${i1}")

    val i2 = r.nextInt(100)
    println(s"${i2.getClass.getSimpleName}: ${i2}")

    val fl1 = r.nextFloat()
    println(s"${fl1.getClass.getSimpleName}: ${fl1}")

    val d1 = r.nextDouble()
    println(s"${d1.getClass.getSimpleName}: ${d1}")

  }
}
/*
int: 1450750788
int: 56
float: 0.21784681
double: 0.6655233760263605
 */
