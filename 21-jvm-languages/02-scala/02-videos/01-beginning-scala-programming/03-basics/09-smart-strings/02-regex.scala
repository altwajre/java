val message = "We are meeting on June 13th of this year, and having lunch at 12:30PM"
var regex = """(\s|[0-9])?[0-9]:[0-5][0-9]\s*(AM|PM)""".r // .r means it is a regex
println(regex.findAllIn(message).toList)

//$ scala 02-regex.scala
//List(12:30PM)
