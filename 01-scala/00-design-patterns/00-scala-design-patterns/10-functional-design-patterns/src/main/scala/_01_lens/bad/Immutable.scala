package _01_lens.bad

/*
Immutable and verbose

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/7a8899ab-7de1-4eec-8a8e-c447e3e5b0ca.xhtml
 */
object Immutable {
  case class Country(name: String, code: String)
  case class City(name: String, country: Country)
  case class Address(number: Int, street: String, city: City)
  case class Company(name: String, address: Address)
  case class User(name: String, company: Company, address: Address)

  def main(args: Array[String]): Unit = {
    val uk = Country("United Kingdom", "uk")
    val london = City("London", uk)
    val buckinghamPalace = Address(1, "Buckinghan Palace Road", london)
    val castleBuilders = Company("Castle Builders", buckinghamPalace)

    val switzerland = Country("Switzerland", "CH")
    val geneva = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)
    val ivan = User("Ivan", castleBuilders, genevaAddress)
    println(ivan)

    println("Capitalize UK code...")

    val ivanFixed = ivan.copy(
      company = ivan.company.copy(
        address = ivan.company.address.copy(
          city = ivan.company.address.city.copy(
            country = ivan.company.address.city.country.copy(
              code = ivan.company.address.city.country.code.toUpperCase
            )
          )
        )
      )
    )

    println(ivanFixed)

  }
}
/*
User(Ivan,Company(Castle Builders,Address(1,Buckinghan Palace Road,City(London,Country(United Kingdom,uk)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
Capitalize UK code...
User(Ivan,Company(Castle Builders,Address(1,Buckinghan Palace Road,City(London,Country(United Kingdom,UK)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
 */
