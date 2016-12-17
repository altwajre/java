def add(x:Int, y:Int) = {
  if (x > 10) (x + y).toString
  else x + y
}

// # bring the code above to REPL to see what compiler is doing
//scala> :paste
//// Entering paste mode (ctrl-D to finish)
//
//def add(x:Int, y:Int) = {
//  if (x > 10) (x + y).toString
//  else x + y
//}
//
//// Exiting paste mode, now interpreting.
//
//add: (x: Int, y: Int)Any <- type is Any

println(add(2,3))

//output:
//5
