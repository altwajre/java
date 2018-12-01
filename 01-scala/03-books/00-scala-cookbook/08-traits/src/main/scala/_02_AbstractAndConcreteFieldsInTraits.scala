/*
Using Abstract and Concrete Fields in Traits

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s03.html

Put abstract or concrete fields in traits so they are declared in one place and available to all types that implement the trait.
 */
object _02_AbstractAndConcreteFieldsInTraits {
  trait PizzaTrait {
    var numToppings: Int     // abstract
    var size = 14            // concrete
    val maxNumToppings = 10  // concrete
  }
  class Pizza extends PizzaTrait {
    override var numToppings: Int = 0  //
    size = 16
  }
  def main(args: Array[String]): Unit = {
    val pizza = new Pizza
    println(s"numToppings: ${pizza.numToppings}, size: ${pizza.size}, maxNumToppings: ${pizza.maxNumToppings}")
  }
}
/*
numToppings: 0, size: 16, maxNumToppings: 10
 */
