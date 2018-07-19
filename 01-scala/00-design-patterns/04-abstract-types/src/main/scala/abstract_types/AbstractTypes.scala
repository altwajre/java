package abstract_types

/*
Abstract types

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/a3ad60d8-6832-4950-aeb2-bf7ff25c1587.xhtml
 */
trait ContainerAT {
  type T
  val data: T
  def compare(other: T) = {
    data.equals(other)
  }
}

class StringContainer(val data: String) extends ContainerAT {
  override type T = String
}

object AbstractTypes {
  def main(args: Array[String]) = {
    val stringContainer = new StringContainer("some text")
    println(s"Comparing with string: ${stringContainer.compare("some text")}")
  }

}
/*
Comparing with string: true
 */
