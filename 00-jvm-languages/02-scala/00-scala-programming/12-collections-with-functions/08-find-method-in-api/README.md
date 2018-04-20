# Finding your method in the API

```
scala> List(1,2,3,4,5,6).partition(x => x % 2 != 0)
res6: (List[Int], List[Int]) = (List(1, 3, 5),List(2, 4, 6))

scala> List("Red", "Orange", "Green", "Grey", "Purple", "Pink").groupBy(x => x.head)
res7: scala.collection.immutable.Map[Char,List[String]] = Map(P -> List(Purple, Pink), G -> List(Green, Grey), R -> List(Red), O -> List(Orange))

scala> (1 to 100).take(10)
res9: scala.collection.immutable.Range = Range 1 to 10

scala> (1 to 100).takeRight(10)
res10: scala.collection.immutable.Range = Range 91 to 100

scala> (1 to 100).takeWhile(x => x < 20)
res12: scala.collection.immutable.Range = Range 1 to 19

scala> List(1,2,3,4,4,3,2,1).distinct
res13: List[Int] = List(1, 2, 3, 4)

scala> List.empty[Int]
res14: List[Int] = List()

scala> List.empty[String]
res15: List[String] = List()

scala> List.empty[List[Int]]
res16: List[List[Int]] = List()

scala> List.fill(10) {
     | val x = 10
     | val y = 10
     | x + y + 8
     | }
res17: List[Int] = List(28, 28, 28, 28, 28, 28, 28, 28, 28, 28)
```
