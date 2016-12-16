# Lazy val

- lazy val will not evaluated until referenced
- Any subsequent calls to the val will return the same value when initially called upon
- There is no such thing as a lazy var
- lazy val can be forgiving if an exception happens

*lazy val*

```
scala> lazy val a = {println("evaluated"); 5}
a: Int = <lazy>

scala> a
evaluated
res0: Int = 5

scala> a
res1: Int = 5
```

*no lazy var*

```
scala> lazy var v = 200
<console>:1: error: lazy not allowed here. Only vals can be lazy
lazy var v = 200
     ^
```

*lazy val can be forgiving if an exception happens*

```
scala> var divisor = 0
divisor: Int = 0

scala> lazy val quotient = 40 / divisor
quotient: Int = <lazy>

scala> println(quotient)
java.lang.ArithmeticException: / by zero
  at .quotient$lzycompute(<console>:12)
  at .quotient(<console>:12)
  ... 30 elided

scala> divisor = 2
divisor: Int = 2

scala> println(quotient)
20
```
