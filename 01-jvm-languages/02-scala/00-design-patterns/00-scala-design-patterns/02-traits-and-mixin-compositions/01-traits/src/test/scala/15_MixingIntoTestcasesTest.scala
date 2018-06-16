import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

case object FooTest1

/*
Mixing into the test cases

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/612d6f23-8159-4427-9b73-8f759d1f25e3.xhtml

We can focus specifically on the trait being tested.
 */

@RunWith(classOf[JUnitRunner])
class MixingIntoTestcasesTest extends FlatSpec with Matchers {
  "hello" should "greet properly." in new A { // Mixing into the test cases
    hello() should equal("Hello, I am trait A!")
  }

  "pass" should "return the right string with the number." in new A {
    pass(10) should equal("Trait A said: 'You passed 10.'")
  }

  it should "be correct also for negative values." in new A {
    pass(-10) should equal("Trait A said: 'You passed -10.'")
  }
}
