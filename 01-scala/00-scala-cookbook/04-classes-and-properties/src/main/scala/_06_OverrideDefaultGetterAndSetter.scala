/*
Overriding Default Accessors and Mutators

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s07.html

setter method named
name_=conforms
 */
object _06_OverrideDefaultGetterAndSetter {
  class Person(private var _name: String) {
    def name = _name            // accessor
    def name_=(aName: String) {  // mutator
      _name = aName
    }
  }
  def main(args: Array[String]): Unit = {
    val person = new Person("Tom")
    person.name = "Tommy"  // setter
    println(person.name)   // getter
  }
}
/*
Tommy
 */
