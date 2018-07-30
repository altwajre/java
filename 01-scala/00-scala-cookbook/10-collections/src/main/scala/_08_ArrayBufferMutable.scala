import scala.collection.mutable.ArrayBuffer

/*
Make ArrayBuffer Your “Go To” Mutable Sequence

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s09.html
 */
object _08_ArrayBufferMutable {
  def main(args: Array[String]): Unit = {
    val nums = ArrayBuffer(1, 2, 3)
    println(nums)

    println("# add one element")
    nums += 4
    println(nums)

    println("# add two or more elements")
    nums += (5, 6)
    println(nums)

    println("# add elements from other collection")
    nums ++= List(7, 8)
    println(nums)

    println("# remove one element")
    nums -= 8
    println(nums)

    println("# remove two or more elements")
    nums -= (6, 7)
    println(nums)

    println("# remove elements specified by another sequence")
    nums --= Array(4, 5)
    println(nums)
  }
}
/*
ArrayBuffer(1, 2, 3)
# add one element
ArrayBuffer(1, 2, 3, 4)
# add two or more elements
ArrayBuffer(1, 2, 3, 4, 5, 6)
# add elements from other collection
ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8)
# remove one element
ArrayBuffer(1, 2, 3, 4, 5, 6, 7)
# remove two or more elements
ArrayBuffer(1, 2, 3, 4, 5)
# remove elements specified by another sequence
ArrayBuffer(1, 2, 3)
 */
