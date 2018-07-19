package _01_lens

import scalaz.Lens

/*
https://www.youtube.com/watch?v=efv0SQNde5Q

Lenses are composable functional, field accessors

getter and setter

case class Lens[A, B] {
  g: A => B,      // getter: from A get B
  s: (B, A) => A  // setter: set B value inside of A; given a B, and replace with A
}

They create actual lenses so that for an object of the A type, the calls get and set a value of the B type.
 */
object LensApp {
  case class Country(name: String, code: String)
  case class City(name: String, country: Country)
  case class Address(number: Int, street: String, city: City)
  case class Company(name: String, address: Address)
  case class User(name: String, company: Company, address: Address)

  object User {
    val userCompany = Lens.lensu[User, Company](
      (u, company) => u.copy(company = company),
      _.company
    )

    val userAddress = Lens.lensu[User, Address](
      (u, address) => u.copy(address = address),
      _.address
    )

    val companyAddress = Lens.lensu[Company, Address](
      (c, address) => c.copy(address = address),
      _.address
    )

    val addressCity = Lens.lensu[Address, City](
      (a, city) => a.copy(city = city),
      _.city
    )

    val cityCountry = Lens.lensu[City, Country](
      (c, country) => c.copy(country = country),
      _.country
    )

    val countryCode = Lens.lensu[Country, String](
      (c, code) => c.copy(code = code),
      _.code
    )

    // compose lenses
    val userCompanyCountryCode = userCompany >=> companyAddress >=> addressCity >=> cityCountry >=> countryCode

    val userCompanyCountryCodeCompose = countryCode <=< cityCountry <=< addressCity <=< companyAddress <=< userCompany
  }

  def main(args: Array[String]): Unit = {
    import User._

    val uk = Country("United Kingdom", "uk")
    val london = City("London", uk)
    val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
    val yahoo = Company("yahoo", buckinghamPalace)

    val switzerland = Country("Switzerland", "CH")
    val geneva = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)

    val ivan = User("Ivan", yahoo, genevaAddress)
    System.out.println(ivan)

    System.out.println("# Capitalize UK code...")
    val ivanFixed = userCompanyCountryCode.mod(_.toUpperCase, ivan)
    println(ivanFixed)

    println("# Lens getter and setter")
    println("## get Company from User")
    println(userCompany.get(ivan))
    println("## set User a new Company - that creates a new user reference")
    val googleIvan = userCompany.set(ivan, Company("google", buckinghamPalace))
    println(googleIvan)
    println("## Display ivan - that is the old user reference")
    println(ivan)

    println("# Lens compose getter and setter")
    println("## get Company-Country-Code from User")
    println(userCompanyCountryCode.get(ivan))
    println("## set User a new Company-Country-Code")
    println(userCompanyCountryCode.set(ivan, "CA"))
  }
}
/*
User(Ivan,Company(yahoo,Address(1,Buckingham Palace Road,City(London,Country(United Kingdom,uk)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
# Capitalize UK code...
User(Ivan,Company(yahoo,Address(1,Buckingham Palace Road,City(London,Country(United Kingdom,UK)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
# Lens getter and setter
## get Company from User
Company(yahoo,Address(1,Buckingham Palace Road,City(London,Country(United Kingdom,uk))))
## set User a new Company - that creates a new user reference
User(Ivan,Company(google,Address(1,Buckingham Palace Road,City(London,Country(United Kingdom,uk)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
## Display ivan - that is the old user reference
User(Ivan,Company(yahoo,Address(1,Buckingham Palace Road,City(London,Country(United Kingdom,uk)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
# Lens compose getter and setter
## get Company-Country-Code from User
uk
## set User a new Company-Country-Code
User(Ivan,Company(yahoo,Address(1,Buckingham Palace Road,City(London,Country(United Kingdom,CA)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
 */
