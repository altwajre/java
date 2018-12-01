/*
Creating Static Members with Companion Objects

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s07.html

- Define instance members in class
- Define static members in companion object
 */
object _06_StaticMembersWithCompanionObjects {
  class Pizza(var crustType: String) {
    override def toString: String = s"Crust type is $crustType"
  }

  // companion object: can be accessed as static members
  object Pizza {
    val CRUST_TYPE_THIN = "thin"
    val CRUST_TYPE_THICK = "thick"
    def getFoo = "Foo"
  }

  def main(args: Array[String]): Unit = {
    println(Pizza.CRUST_TYPE_THICK)
    println(Pizza.getFoo)
  }
}
/*
thick
Foo
 */
