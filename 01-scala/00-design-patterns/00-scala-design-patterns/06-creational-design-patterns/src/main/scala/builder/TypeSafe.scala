package builder

/*
Using generalized type constraints

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0757c0f9-8b73-4a80-a766-5e8b2e0d1793.xhtml

Type-safe builder - create a builder that validates all requirements are satisfied during compile time.

Every person must have `firstName` first and `lastName`
 */

// Sum ADTs - define the different steps of build progress
object TypeSafe {

  sealed trait BuildStep

  sealed trait HasFirstName extends BuildStep

  sealed trait HasLastName extends BuildStep

  class Person(val firstName: String, val lastName: String, val age: Int)

  // <: Upper Type Bounds
  // PersonBuilder has generic BuildStep
  class PersonBuilder[PassedStep <: BuildStep] private(
                                                        var firstName: String,
                                                        var lastName: String,
                                                        var age: Int
                                                      ){
    protected def this() = this("", "", 0)
    protected def this(pb: PersonBuilder[_]) = this (
      pb.firstName,
      pb.lastName,
      pb.age
    )

    def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
      this.firstName = firstName
      new PersonBuilder[HasFirstName](this) // for required field, return new PersonBuilder[HasFirstName]
    }

    def setLastName(lastName: String) (implicit ev: PassedStep =:= HasFirstName): PersonBuilder[HasLastName] = {
      this.lastName = lastName
      new PersonBuilder[HasLastName](this) // for required field, return new PersonBuilder[HasLastName]
    }

    def setAge(age: Int): PersonBuilder[PassedStep] = {
      this.age = age
      this // for non-required field, return this
    }

    // build() can only be called on a builder which has passed the HasLastName step.
    def build()(implicit ev: PassedStep =:= HasLastName): Person = new Person(firstName, lastName, age)
  }

  object PersonBuilder {
    def apply() = new PersonBuilder[BuildStep]()
  }

  def main(args: Array[String]): Unit = {
    val person = PersonBuilder()
      .setFirstName("Tom")
      .setLastName("Lee") // compile error occurs after comment out set lastName because lastName is a required field
      .setAge(28)
      .build()
    println(s"Person: ${person.firstName} ${person.lastName} is ${person.age} years old")
  }
}
/*
Person: Tom Lee is 28 years old
 */
