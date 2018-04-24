object FilterFunction extends App {
  val a = 1 to 10
  println(a.filter(x => x % 2 == 0)) //Vector(2, 4, 6, 8, 10) - Filter Even Numbers
  println(a.filterNot(_ % 2 == 0)) //Vector(1, 3, 5, 7, 9) - Filter Odd Numbers
  println(a.exists(_ % 2 == 0)) //true - _ means it (this)

  def filterVowels(s:String) = s.toLowerCase.filter(c => Set('a', 'e', 'i', 'o', 'u').contains(c))
  println(filterVowels("Orange")) //oae

  val b = Set("Red", "Blue", "Green", "Purple", "Orange")
  println(b.filter(s => filterVowels(s).size > 1)) //Set(Blue, Green, Purple, Orange)

  val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")
  println(m.filterKeys(_ % 2 == 0)) // Map(2 -> Two, 4 -> Four)

  println(Some(5).filter(_ % 2 == 0)) //None
  println(Some(4).filter(_ % 2 == 0)) //Some(4)
}
