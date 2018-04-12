val coupleIntString = new Couple(10, "Scala") //Couple[Int, String]
val coupleIntString2:Couple[Int, String] = Couple(10, "Scala") //Couple[Int, String]
val coupleStringString = Couple("One", "Two") //Couple[String, String]
val coupleDoubleInt = Couple(10.12, 8) //Couple[Double, Int]
val coupleStringCoupleIntDouble = Couple("Hello", Couple(8, 10.12)) //Couple[String, Couple[Int, Double]]

println(coupleStringCoupleIntDouble.second.second) //second means the second arg
