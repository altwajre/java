def add(x: Int, y: Int): Int = x + y // :Int is return type
println(add(2, 3))

def numberStatus(a: Int) =
  if (a < 10) "Less than 10"
  else if (a > 10) "Greater than 10"
  else "It is 10!"

// # bring the code above to REPL to see what compiler is doing
//scala> :paste
//// Entering paste mode (ctrl-D to finish)
//
//def numberStatus(a: Int) =
//  if (a < 10) "Less than 10"
//  else if (a > 10) "Greater than 10"
//  else "It is 10!"
//ctrl+d to finish
//// Exiting paste mode, now interpreting.
//
//numberStatus: (a: Int)String

println(numberStatus(10))

//output:
//5
//It is 10!
