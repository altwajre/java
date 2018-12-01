import scala.collection.{SortedMap, mutable}

/*
Creating Maps
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s13.html

Choosing a Map Implementation
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s14.html

Adding, Updating, and Removing Elements with a Mutable Map
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s15.html

Accessing Map Values
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s17.html

Reversing Keys and Values
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s20.html

Testing for the Existence of a Key or Value in a Map
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s21.html

Filtering a Map
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s22.html

Sorting an Existing Map by Key or Value
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s23.html

Finding the Largest Key or Value in a Map
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s24.html
 */
object MapApp {
  def main(args: Array[String]): Unit = {
    println("# immutable map")
    val states = Map("AL" -> "Alabama", "AK" -> "Alaska")
    println(states)

    println("# mutable map")
    var customers = collection.mutable.Map("1" -> "Tom")
    customers += ("2" -> "Dick")
    println(customers)

    println("# sorted map")
    val grades = SortedMap("Kim" -> 90, "Al" -> 85, "Melissa" -> 95, "Emily" -> 91, "Hannah" -> 92)
    println(grades)

    println("# LinkedHashMap - insert in tail")
    val users = mutable.LinkedHashMap("1" -> "Tom")
    println(users)
    users += ("2" -> "Dick")
    println(users)
    users += ("3" -> "Harry")
    println(users)

    println("# ListMap - insert in head")
    val players = mutable.ListMap("1" -> "Tom")
    println(players)
    players += ("2" -> "Dick")
    println(players)

    println("# Add")
    players += ("3" -> "Harry")
    println(players)

    println("# Update")
    players("1") = "Tommy"
    println(players)

    println("# Remove")
    players -= "2"
    println(players)

    println("# Access map value")
    println(players.get("1"))
    println(players.getOrElse("FOO", "No such player"))

    println("# Traverse Map")
    println("- for loop")
    for ((k, v) <- players) println(s"key: $k, value: $v")

    println("- foreach")
    players.foreach {
      case (id, name) => println(s"key: $id, value: $name")
    }

    println("- keys.foreach")
    players.keys.foreach(println)

    println("- values.foreach")
    players.values.foreach(println)

    println("# Reverse Keys and Values")
    println("- before")
    println(players)
    val reverseMap = for ((k, v) <- players) yield (v, k)
    println("- after")
    println(reverseMap)

    println("# Testing for existence key or value of map")
    val hasRry = players.valuesIterator.exists(_.contains("rry"))
    println(hasRry)

    println("# Filtering a map")

    println("## mutable maps")

    var writers = mutable.Map(1 -> "Tom", 2 -> "Dick", 3 -> "Harry")

    println("- .retain()")
    println(writers.retain((k, v) => k > 1))

    println("- .transform()")
    println(writers.transform((k, v) => v.toUpperCase))

    println("## immutable maps")

    val readers = Map(1 -> "Tom", 2 -> "Dick", 3 -> "Harry")

    println("- .filterKeys(_ > 2)")
    println(readers.filterKeys(_ > 2))

    println("- .filterKeys(Set(2, 3))")
    println(readers.filterKeys(Set(2, 3)))

    println("- .filter((t) => t._1 > 1)")
    println(readers.filter((t) => t._1 > 1))

    println("""- readers.filter((t) => t._2 == "c")""")
    println(readers.filter((t) => t._2 == "c"))

    println("- .take(2)")
    println(readers.take(2))

    println("# Finding the Largest Key or Value in a Map")
    println(readers.keysIterator.max)
  }
}

/*
# immutable map
Map(AL -> Alabama, AK -> Alaska)
# mutable map
Map(2 -> Dick, 1 -> Tom)
# sorted map
Map(Al -> 85, Emily -> 91, Hannah -> 92, Kim -> 90, Melissa -> 95)
# LinkedHashMap - insert in tail
Map(1 -> Tom)
Map(1 -> Tom, 2 -> Dick)
Map(1 -> Tom, 2 -> Dick, 3 -> Harry)
# ListMap - insert in head
Map(1 -> Tom)
Map(2 -> Dick, 1 -> Tom)
# Add
Map(3 -> Harry, 1 -> Tom, 2 -> Dick)
# Update
Map(1 -> Tommy, 3 -> Harry, 2 -> Dick)
# Remove
Map(3 -> Harry, 1 -> Tommy)
# Access map value
Some(Tommy)
No such player
# Traverse Map
- for loop
key: 3, value: Harry
key: 1, value: Tommy
- foreach
key: 3, value: Harry
key: 1, value: Tommy
- keys.foreach
3
1
- values.foreach
Harry
Tommy
# Reverse Keys and Values
- before
Map(3 -> Harry, 1 -> Tommy)
- after
Map(Tommy -> 1, Harry -> 3)
# Testing for existence key or value of map
true
# Filtering a map
## mutable maps
- .retain()
Map(2 -> Dick, 3 -> Harry)
- .transform()
Map(2 -> DICK, 3 -> HARRY)
## immutable maps
- .filterKeys(_ > 2)
Map(3 -> Harry)
- .filterKeys(Set(2, 3))
Map(2 -> Dick, 3 -> Harry)
- .filter((t) => t._1 > 1)
Map(2 -> Dick, 3 -> Harry)
- readers.filter((t) => t._2 == "c")
Map()
- .take(2)
Map(1 -> Tom, 2 -> Dick)
# Finding the Largest Key or Value in a Map
3
 */
