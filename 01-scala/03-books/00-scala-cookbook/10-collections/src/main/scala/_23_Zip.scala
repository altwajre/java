/*
Merging Two Sequential Collections into Pairs with zip

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s24.html
 */
object _23_Zip {
  def main(args: Array[String]): Unit = {
    val women = List("Wilma", "Betty")
    val men = List("Fred", "Barney")
    println("# zip")
    val couples = women zip men
    println(couples)

    println("# for loop")
    for((wife, husband) <- couples) {
      println(s"$wife is married to $husband")
    }

    println("# map")
    println(couples.toMap)
  }
}
/*
# zip
List((Wilma,Fred), (Betty,Barney))
# for loop
Wilma is married to Fred
Betty is married to Barney
# map
Map(Wilma -> Fred, Betty -> Barney)
 */
