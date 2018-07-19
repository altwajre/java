package _01_lens.bad

/*
Using mutable properties

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/29ff41d1-baa5-4631-b7c5-87f4bb215efc.xhtml
 */
object Mutable {
  case class Country(var name: String, var code: String)
  case class City(var name: String, var country: Country)
  case class Address(var number: Int, var street: String, var city: City)
  case class Company(var name: String, var address: Address)
  case class User(var name: String, var company: Company, var address: Address)

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
    ivan.company.address.city.country.code = ivan.company.address.city.country.code.toUpperCase
    println(ivan)
  }
}
/*
User(Ivan,Company(Castle Builders,Address(1,Buckinghan Palace Road,City(London,Country(United Kingdom,uk)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
Capitalize UK code...
User(Ivan,Company(Castle Builders,Address(1,Buckinghan Palace Road,City(London,Country(United Kingdom,UK)))),Address(1,Geneva Lake,City(geneva,Country(Switzerland,CH))))
 */
