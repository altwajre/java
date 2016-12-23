object Ranges extends App {
  var r = 1 to 10 //include 10
  var r2 = 1 until 10 //exclude 10
  println(r) //Range 1 to 10
  println(r2) // Range 1 until 10
  println(r2.head) //1
  println(r2.tail) //Range 2 until 10

  val r3 = 2 to 10 by 2 //Range 2 to 10 by 2
  println(r3)

  val r4 = 10 to 2 by -2 //Range 10 to 2 by -2
  println(r4)

  val r5 = ('a' to 'z') ++ ('A' to 'Z') //concate
  println(r5) //Vector(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z)

  val r6 = Range(1,10)
  println(r6) //Range 1 until 10

  val r7 = Range(1,10,2)
  println(r7) //inexact Range 1 until 10 by 2

  val r8 = Range.inclusive(1,10)
  println(r8) //Range 1 to 10

  val r9 = Range.inclusive(2,10,2)
  println(r9) //Range 2 to 10 by 2

  println("#for 1 to 10")
  for(i <- 1 to 10) print("%s ".format(i))

  println("\n#for 2 to 10 by 2")
  for(i <- 2 to 10 by 2) print("%s ".format(i))
}