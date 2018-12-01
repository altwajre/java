/*
Defining Auxiliary Constructors

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s04.html

Additional constructors
 */
object _03_AdditionalConstructors {
  // primary constructor
  class Pizza(var crustSize: Int, var crustType: String) {
    // one-arg additional constructor
    def this(crustSize: Int) {
      this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
    }

    // one-arg additional constructor
    def this(crustType: String) {
      this(Pizza.DEFAULT_CRUST_SIZE, crustType)
    }

    // zero-arg auxiliary constructor
    def this() {
      this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
    }

  }
  object Pizza {
    val DEFAULT_CRUST_SIZE = 12
    val DEFAULT_CRUST_TYPE = "THIN"
  }

  def main(args: Array[String]): Unit = {
    val p1 = new Pizza(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
    val p2 = new Pizza(Pizza.DEFAULT_CRUST_SIZE)
    val p3 = new Pizza(Pizza.DEFAULT_CRUST_TYPE)
    val p4 = new Pizza()
  }
}
