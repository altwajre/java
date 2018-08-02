import scala.collection.mutable.ListBuffer

/*
Deleting Elements from a List (or ListBuffer)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s05.html
 */
object _04_DeleteElementsFromList {
  def main(args: Array[String]): Unit = {
    println("# list.filter()")
    val list = List(5, 1, 4, 3, 2)
    println(list)
    val newList = list.filter(_ > 2)
    println(newList)

    println("# ListBuffer")
    val listBuffer = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8)
    println(listBuffer)
    println("- delete one element")
    listBuffer -= 5
    println(listBuffer)
    println("- delete multiple elements")
    listBuffer -= (2, 3)
    println(listBuffer)
    println("- delete elements by position")
    listBuffer.remove(0)
    println(listBuffer)
    listBuffer.remove(1, 3)
    println(listBuffer)

    println("# delete multiple elements are specified in another collection")
    val x = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8)
    println(x)
    x --= Seq(1, 2, 3)
    println(x)
  }
}
/*
# list.filter()
List(5, 1, 4, 3, 2)
List(5, 4, 3)
# ListBuffer
ListBuffer(1, 2, 3, 4, 5, 6, 7, 8)
- delete one element
ListBuffer(1, 2, 3, 4, 6, 7, 8)
- delete multiple elements
ListBuffer(1, 4, 6, 7, 8)
- delete elements by position
ListBuffer(4, 6, 7, 8)
ListBuffer(4)
# delete multiple elements are specified in another collection
ListBuffer(1, 2, 3, 4, 5, 6, 7, 8)
ListBuffer(4, 5, 6, 7, 8)
 */
