package com.company.app._12CollectionsWithFunctions

object _02FilterFilternotExists extends App{
  val a = 1 to 10
  println(a.filter(x => x % 2 == 0))
  // Vector(2, 4, 6, 8, 10)
  println(a.filterNot(x => x % 2 == 0))
  // Vector(1, 3, 5, 7, 9)
  println(a.exists(x => x % 2 == 0))
  // true

  def filterVowels(s:String) =
    s.toLowerCase.filter(c => Set('a', 'e', 'i', 'o', 'u').contains(c))
  println(filterVowels("Orange"))
  // oae

  val b = Set("Red", "Blue", "Green")
  println(b.filter(s => filterVowels(s).size > 1))
  // Set(Blue, Green)

  val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")
  println(m.filterKeys(x => x % 2 == 0))
  // Map(2 -> Two, 4 -> Four)

  println(Some(5).filter(x => x % 2 == 0))
  // None
  println(Some(4).filter(x => x % 2 == 0))
  // Some(4)
  println(Some(4).get)
  // 4
}
