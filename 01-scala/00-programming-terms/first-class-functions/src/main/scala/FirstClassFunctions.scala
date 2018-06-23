/*
Function literals

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/2fdb7813-2c2b-4d14-90e8-cee4c0cea84d.xhtml

 */

class FirstClassFunctions {
  // Assign a function to a variable
  val square = (x: Int) => x * x

  // Pass a function as a parameter
  def runOperation(f: (Int, Int) => Int, a: Int, b: Int): Int = {
    f(a, b)
  }

  // Return a wrapText function
  def htmlTag(tag: String) = (msg: String) => {
    println(s"<${tag}>${msg}</${tag}>")
  }

}

object FirstClassFunctions {

  def main(args: Array[String]) = {
    val obj = new FirstClassFunctions
    println("# Test assign a function a variable")
    println(s"3 square = ${obj.square(3)}") // Test assign a function a variable

    println("# Test pass a function as a variable")
    println(obj.runOperation(Math.max, 10, 20)) // Test pass a function as a variable

    println("# Test return a function from function")
    val printH1 = obj.htmlTag("h1") // Test return a function from function
    printH1("Headline 1")
    printH1("Another Headline")

    val printP = obj.htmlTag("p")
    printP("Paragraph 1")
  }
}
/*
# Test assign a function a variable
3 square = 9
# Test pass a function as a variable
20
# Test return a function from function
<h1>Headline 1</h1>
<h1>Another Headline</h1>
<p>Paragraph 1</p>
 */
