import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/*
Mixing into the test class

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/fd37fb23-bcd9-4f3f-9ac9-2ffd0ed93842.xhtml

Mixing in a trait into a test class is only possible if the trait does not extend any other class explicitly.
 */
@RunWith(classOf[JUnitRunner])
class TraitATest extends FlatSpec with Matchers with A {
  "hello" should "greet properly." in {
    hello() should equal("Hello, I am trait A!")
  }

  "pass" should "return the right string with the number." in {
    pass(10) should equal("Trait A said: 'You passed 10.'")
  }

  it should "be correct also for negative values." in {
    pass(-10) should equal("Trait A said: 'You passed -10.'")
  }
}
