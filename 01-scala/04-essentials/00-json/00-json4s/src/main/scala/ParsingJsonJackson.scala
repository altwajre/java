import org.json4s.jackson.JsonMethods._

object ParsingJsonJackson {
  def main(args: Array[String]): Unit = {
    val intList = parse(""" { "numbers" : [1, 2, 3, 4] } """)
    println(intList)
    val doubleList = parse("""{"name":"Toy","price":35.35}""", useBigDecimalForDouble = true)
    println(doubleList)
  }
}
/*
JObject(List((numbers,JArray(List(JInt(1), JInt(2), JInt(3), JInt(4))))))
JObject(List((name,JString(Toy)), (price,JDecimal(35.35))))
 */
