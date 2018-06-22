/*
Lower Type Bounds

https://docs.scala-lang.org/tour/lower-type-bounds.html

The class Node and its subtypes are covariant because we have +B
 */
trait Node[+B] {
  def prepend[U >: B](elem: U): Node[U]
}

case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
  def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)

  def head: B = h

  def tail: Node[B] = t
}

case class Nil[+B]() extends Node[B] {
  def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
}

trait Bird

case class AfricanSwallow() extends Bird

case class EuropeanSwallow() extends Bird

object LowerTypeBounds {
  def main(args: Array[String]) = {
    val africanSwallowList = ListNode[AfricanSwallow](AfricanSwallow(), Nil())
    println(africanSwallowList.h.getClass.getSimpleName)
    val birdList: Node[Bird] = africanSwallowList
    birdList.prepend(new EuropeanSwallow)
    println(birdList)
  }
}
/*
AfricanSwallow
ListNode(AfricanSwallow(),Nil())
 */
