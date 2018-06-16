import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/*
Using a class

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/827e6820-a105-4051-8d0b-dd53d1d87d0f.xhtml

Create a dummy class inside the test class and then use it
 */
class MultiplierIdentity {
  def identity: Int = 1
}

trait DoubledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 2 * super.identity
}

@RunWith(classOf[JUnitRunner])
class DoubledMultiplierIdentityTest extends FlatSpec with Matchers {
  class DoubledMultiplierIdentityClass extends DoubledMultiplierIdentity // create a dummy class

  private val instance = new DoubledMultiplierIdentityClass

  "identity" should "return 2 * 1" in {
    instance.identity should equal(2)
  }

}
