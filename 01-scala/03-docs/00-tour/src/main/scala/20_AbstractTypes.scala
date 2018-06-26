/*
Abstract types

https://docs.scala-lang.org/tour/abstract-types.html

Traits and abstract classes can have an abstract type member.
This means that the concrete implementation define the actual type.
 */

object AbstractTypes {
  def main(args: Array[String]) = {
    trait Buffer {
      type T // T describe the type of element
      val element: T
    }
    abstract class SeqBuffer extends Buffer {
      type U
      type T <: Seq[U] // add type U that is upper-type-bound to T
      def length = element.length
    }
    abstract class IntSeqBuffer extends SeqBuffer {
      type U = Int
    }
    def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer = new IntSeqBuffer {
      override type T = List[U]
      override val element = List(elem1, elem2)
    }

    val buf = newIntSeqBuf(7, 8)
    println("length = " + buf.length)
    println("content = " + buf.element)
  }
}
/*
length = 2
content = List(7, 8)
 */

/*
turn abstract members into type parameters of classes
 */
object TypeParameters {
  def main(args: Array[String]) = {
    abstract class Buffer[+T] {
      val element: T
    }
    abstract class SeqBuffer[U, +T <: Seq[U]] extends Buffer[T] {
      def length = element.length
    }
    def newIntSeqBuf(e1: Int, e2: Int): SeqBuffer[Int, Seq[Int]] = new SeqBuffer[Int, List[Int]] {
      val element = List(e1, e2)
    }

    val buf = newIntSeqBuf(7, 8)
    println("length = " + buf.length)
    println("content = " + buf.element)

  }
}
/*
length = 2
content = List(7, 8)
 */
