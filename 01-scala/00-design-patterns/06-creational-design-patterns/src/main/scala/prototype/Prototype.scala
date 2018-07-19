package prototype

/*
The prototype design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/47e4732f-7745-4b1f-966a-d0c158951b39.xhtml

The prototype design pattern is a creational design pattern that involves creating objects by cloning them from existing ones.
Its purpose is related to performance to avoid expensive calls.

case classes have a copy method which returns a new instance that is cloned from the original one.
 */
object Prototype {
  case class Cell(dna: String, proteins: List[String])

  def main(args: Array[String]) = {
    val initialCell = Cell("abcd", List("protein1", "protein2"))
    val copy1 = initialCell.copy()
    val copy2 = initialCell.copy()
    val copy3 = initialCell.copy(dna = "1234")
    println(s"The prototype is: ${initialCell}")
    println(s"Cell 1: ${copy1}")
    println(s"Cell 2: ${copy2}")
    println(s"Cell 3: ${copy3}")
    println(s"are 1 and 2 equal: ${copy1 == copy2}")
    println(s"are 1 and 3 equal: ${copy1 == copy3}")
  }
}
/*
The prototype is: Cell(abcd,List(protein1, protein2))
Cell 1: Cell(abcd,List(protein1, protein2))
Cell 2: Cell(abcd,List(protein1, protein2))
Cell 3: Cell(1234,List(protein1, protein2))
are 1 and 2 equal: true
are 1 and 3 equal: false
 */
