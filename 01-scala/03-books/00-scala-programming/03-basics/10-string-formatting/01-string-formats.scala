// Older Java Style
val str = String.format("This is a %s", "Test")
println(str)

// Scala's Style
val str2 = "This is a %s".format("Test")
println(str2)

println("Because you're %s, %s, %s times a lady".format("Three", "Twice", "Once"))

// Position arguments
println("Because you're %3$s, %2$s, %1$s times a lady".format("Three", "Twice", "Once"))

import java.time._
// Use the first argument over and over to separate the month, date, and year
println("We will be eating lunch on %1$tB the %1$te in the year %1$tY".format(LocalDate.now))

val lyrics =
  """I see trees of %s
    |%s roses too
    |I see them bloom
    |For me and you
    |And I think to myself
    |What a wonderful world
  """.stripMargin.format("green", "Red")
println(lyrics)

//$ scala 01-string-formats.scala
//This is a Test
//This is a Test
//Because you're Three, Twice, Once times a lady
//Because you're Once, Twice, Three times a lady
//We will be eating lunch on December the 16 in the year 2016
//I see trees of green
//Red roses too
//I see them bloom
//For me and you
//And I think to myself
//What a wonderful world
