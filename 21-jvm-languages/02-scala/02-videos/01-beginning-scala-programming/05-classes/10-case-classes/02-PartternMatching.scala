val toys1 = Department("Toys")
println(toys1)
val name = toys1 match {
  case Department(n) => n + "!"
  case _ => "Unknown"
}
println(name)

val Department(name2) = toys1 // extract name property from Department
println(name2)
