/*
Function literals

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/2fdb7813-2c2b-4d14-90e8-cee4c0cea84d.xhtml

 */

object AssignFunctionToVariable {
  // Assign a function to a variable
  val square: Int => Int = (x: Int) => x * x

  val add: (Int, Int) => Int = (a: Int, b: Int) => a + b

  def main(args: Array[String]): Unit = {
    println("# square(3)")
    val squareResult = square(3)
    println(squareResult)

    println("# add(2, 3)")
    val addResult = add(2, 3)
    println(addResult)
  }
}
/*
# square(3)
9
# add(2, 3)
5
 */

object PassFunctionAsParameter {
  // (Int, Int) => Int means two input Ints and returns Int
  def runOpertion(f: (Int, Int) => Int, a: Int, b: Int): Int = {
    f(a, b)
  }

  def main(args: Array[String]): Unit = {
    println("# Math.addExact(2, 3)")
    val mathAddResult = Math.addExact(2, 3)
    println(mathAddResult)

    println("# runOpertion(Math.addExact, 3, 4)")
    val runOpertionResult = runOpertion(Math.addExact, 3, 4)
    println(runOpertionResult)

  }
}
/*
# Math.addExact(2, 3)
5
# runOpertion(Math.addExact, 3, 4)
7
 */

// Currying: Method takes one arg and returns a function
object ReturnFunction {
  def htmlTag(tag: String): String => Unit = (msg: String) => {
    println(s"<${tag}>${msg}</${tag}>")
  }

  def addStringLength(a: Int): String => Int = (msg: String) => {
    a + msg.length
  }

  def main(args: Array[String]): Unit = {
    println("# String => Unit returned function")
    val printH1 = htmlTag("h1")
    printH1("Headline 1")
    val printP = htmlTag("p")
    printP("Paragraph 1")

    println("# String => Int returned function")
    val addTwo: String => Int = addStringLength(2)
    println(s"addTwo is ${addTwo.getClass.getSimpleName}")
    val twoPlusHelloLen = addTwo("hello")
    println(twoPlusHelloLen)
  }
}
/*
# String => Unit returned function
<h1>Headline 1</h1>
<p>Paragraph 1</p>
# String => Int returned function
addTwo is ReturnFunction$$$Lambda$6/209813603
7
 */
