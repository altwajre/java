val a = 99
println(s"${a} luftballons floating in the summer sky") // s"" means using string interpolation
println(s"${a + 3} luftballons floating in the summer sky")

println("#f interpolation")
val ticketCost = 50
val bandName = "Psychedelic Furs"
val percenIncrease = 20
val musicGenre = "New Wave"
println(f"The $bandName%s tickets are probably $$$ticketCost%1.2f%nThat's a $percenIncrease%% bump because everyone likes $musicGenre")
println("#smart string")
println(
  f"""The $bandName%s tickets are probably $$$ticketCost%1.2f
     |That's a $percenIncrease%% bump because everyone likes $musicGenre""".stripMargin)

//$ scala 01-string-interpolation.scala
//99 luftballons floating in the summer sky
//102 luftballons floating in the summer sky
//f interpolation
//The Psychedelic Furs tickets are probably $50.00
//That's a 20% bump because everyone likes New Wave
//#smart string
//The Psychedelic Furs tickets are probably $50.00
//That's a 20% bump because everyone likes New Wave
