/*
Using Parameter Names When Calling a Method

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s05.html
 */
object _04_CallMethodWithParamNames {
  class Pizza {
    var crustSize = 12
    var crustType = "Thin"
    def update(crustSize: Int, crustType: String): Unit = {
      this.crustSize = crustSize
      this.crustType = crustType
    }

    override def toString: String = "A %d inch %s crust pizza.".format(crustSize, crustType)
  }
  def main(args: Array[String]): Unit = {
    val pizza = new Pizza
    pizza.update(crustSize = 16, crustType = "Thick")
    println(pizza)
  }
}
/*
A 16 inch Thick crust pizza.
 */
