/*
Forcing Callers to Leave Parentheses off Accessor Methods

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s07.html
 */
object _06_MethodsWithParantheses {
  class Pizza {
    // no parentheses after crustSize method
    def crustSize = 12
  }

  def main(args: Array[String]): Unit = {
    val pizza = new Pizza
    println(pizza.crustSize)
  }
}
/*
12
 */
