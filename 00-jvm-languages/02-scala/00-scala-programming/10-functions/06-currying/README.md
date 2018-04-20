# Currying

- Currying is taking a set of arguments and turning them into a sequence of functions returning functions
- You can convert a uncurried function into a curried function by calling curried on that function
- Use Function.uncurried to uncurry a function
- Currying is named after mathematician Haskell Curry

```
scala> val g = (x:Int) => (y:Int) => x + y
g: Int => (Int => Int) = $$Lambda$1063/427939937@118ffcfd

scala> val f = (x:Int, y:Int) => x + y
f: (Int, Int) => Int = $$Lambda$1045/1907808127@1866da85

scala> val fc = f.curried
fc: Int => (Int => Int) = scala.Function2$$Lambda$1052/2115597658@1947596f

scala> Function.uncurried(fc)
res1: (Int, Int) => Int = scala.Function$$$Lambda$1077/871554897@107bfcb2
```
