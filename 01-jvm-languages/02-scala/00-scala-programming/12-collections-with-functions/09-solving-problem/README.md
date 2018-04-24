# Solving Functionally With Scala

```
scala> val groceries = List("Apples", "Milk", "Naan", "Eggs", "Oranges", "Almonds", "Peanut Butter")
groceries: List[String] = List(Apples, Milk, Naan, Eggs, Oranges, Almonds, Peanut Butter)

scala> groceries.zipWithIndex.map(t => (t._1, t._2 + 1)).map(t => t.swap).map(t => s"${t._1}. ${t._2}").mkString("\n")
res19: String =
1. Apples
2. Milk
3. Naan
4. Eggs
5. Oranges
6. Almonds
7. Peanut Butter

scala> groceries.view.zipWithIndex.map(t => (t._1, t._2 + 1)).map(_.swap).map(t => s"${t._1}. ${t._2}").mkString("\n")
res20: String =
1. Apples
2. Milk
3. Naan
4. Eggs
5. Oranges
6. Almonds
7. Peanut Butter
```
