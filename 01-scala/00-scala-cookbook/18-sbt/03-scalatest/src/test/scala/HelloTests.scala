import org.scalatest.FunSuite

class HelloTests extends FunSuite {
  test("the name is set correctly in constructor") {
    val p = Person("Tom")
    assert(p.name == "Tom")
  }

  test("a Person's name can be changed") {
    val p = Person("Dick")
    p.name = "Harry"
    assert(p.name == "Harry")
  }
}
