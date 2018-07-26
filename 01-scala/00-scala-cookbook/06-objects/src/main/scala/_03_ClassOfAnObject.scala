/*
Determining the Class of an Object

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s04.html
 */
object _03_ClassOfAnObject {
  def printAll(numbers: Int*): Unit = {
    println(s"class: ${numbers.getClass}")
  }

  def main(args: Array[String]): Unit = {
    printAll(1, 2, 3)
  }
}
/*
class: class scala.collection.mutable.WrappedArray$ofInt
 */
