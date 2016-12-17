var a = 0
def sideEffect() {
  a += 1
}
sideEffect()
println(a)

//$ scala 02-side-effect-state.scala
//1
