/*
Generic classes

https://docs.scala-lang.org/tour/generic-classes.html
 */
class Stack[A] {
  private var elements: List[A] = List.empty
  def push(x: A) { elements = x :: elements}
  def peek: A = elements.head
  def pop(): A = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }
}

object GenericClass {
  def main(args: Array[String]) = {
    println("# Int")
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    println(stack.pop)
    println(stack.pop)

    println("# subtypes")
    class Fruit
    class Apple extends Fruit
    class Banana extends Fruit
    val fruitStack = new Stack[Fruit]
    val apple = new Apple
    val banana = new Banana
    fruitStack.push(apple)
    fruitStack.push(banana)
    println(fruitStack.pop)
    println(fruitStack.pop)
  }
}
/*
# Int
2
1
# subtypes
GenericClass$Banana$1@7cf10a6f
GenericClass$Apple$1@7e0babb1
 */
