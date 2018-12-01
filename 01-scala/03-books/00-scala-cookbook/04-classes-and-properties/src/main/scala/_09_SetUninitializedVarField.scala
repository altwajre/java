/*
Setting Uninitialized var Field Types

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s10.html
 */
object _09_SetUninitializedVarField {

  case class Address(city: String, state: String, zip: String)

  case class Person(var username: String, var password: String) {
    var age = 0
    var firstName = ""
    var lastName = ""
    var address = None: Option[Address]
  }

  def main(args: Array[String]): Unit = {
    val person = Person("alvinalexander", "secret")
    person.address = Some(Address("Talkeetna", "AK", "99676"))
    person.address.foreach(a => {
      println(a.city)
      println(a.state)
      println(a.zip)
    })
  }
}
/*
Talkeetna
AK
99676
 */
