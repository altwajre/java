/*
Class Composition with Mixins

https://docs.scala-lang.org/tour/mixin-class-composition.html

 */
abstract class A {
  val message: String
}

class B extends A {
  override val message: String = "instance of class B"
}

trait C extends A {
  def loudMessage = message.toUpperCase()
}

/*
Class D has superclass B and a mixin C.
Classes can only one superclass but many mixins.
 */
class D extends B with C

object Mixins1 {
  def main(args: Array[String]) = {
    println("Mixins are traits which are used to compose a class")
    val d = new D
    println(d.message)
    println(d.loudMessage)
  }
}

/*
instance of class B
INSTANCE OF CLASS B
 */

abstract class AbsIterator {
  type T

  def hasNext: Boolean

  def next(): T
}

class StringIterator(s: String) extends AbsIterator {
  override type T = Char
  private var i = 0

  override def hasNext: Boolean = i < s.length

  override def next() = {
    val ch = s charAt (i)
    i += 1
    ch
  }
}

trait RichIterator extends AbsIterator {
  def foreach(f: T => Unit) = while (hasNext) f(next())
}

class RichStringIter extends StringIterator("Rich") with RichIterator

object Mixins2 {
  def main(args: Array[String]) = {
    val richStringIter = new RichStringIter
    richStringIter.foreach(print)
  }
}
/*
Rich
 */
