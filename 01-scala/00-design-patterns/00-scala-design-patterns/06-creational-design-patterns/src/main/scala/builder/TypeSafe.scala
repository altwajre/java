package builder

/*
Using generalized type constraints

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0757c0f9-8b73-4a80-a766-5e8b2e0d1793.xhtml

Type-safe builder - create a builder that validates all requirements are satisfied during compile time.
 */

// Sum ADTs
sealed trait BuildStep

sealed trait HasFirstName extends BuildStep

sealed trait HasLastName extends BuildStep

class User(val firstName: String, val lastName: String, val age: Int)

class UserBuilder[PassedStep <: BuildStep] private(
                                                    var firstName: String,
                                                    var lastName: String,
                                                    var age: Int
                                                  ){
  protected def this() = this("", "", 0)
  protected def this(ub: UserBuilder[_]) = this (
    ub.firstName,
    ub.lastName,
    ub.age
  )

  def setFirstName(firstName: String): UserBuilder[HasFirstName] = {
    this.firstName = firstName
    new UserBuilder[HasFirstName](this)
  }

  def setLastName(lastName: String) (implicit ev: PassedStep =:= HasFirstName): UserBuilder[HasLastName] = {

  }

}

class TypeSafe {

}
