# Curried Method Parameters

- Curried Parameters form groups of parameters
- Curried Parameters make partially applying them easier
- Curried Parameters are often used with implicits

```
scala> def foo(x:Int, y:Int, z:Int) = x + y + z
foo: (x: Int, y: Int, z: Int)Int

scala> val f = foo _
f: (Int, Int, Int) => Int = $$Lambda$1083/823263265@3df1a1ac

scala> def bar(x:Int)(y:Int)(z:Int) = x + y + z
bar: (x: Int)(y: Int)(z: Int)Int

scala> val b = bar _
b: Int => (Int => (Int => Int)) = $$Lambda$1084/918145945@4130955c

scala> def baz(x:Int, y:Int)(z:Int) = x + y + z
baz: (x: Int, y: Int)(z: Int)Int

scala> val c = baz _
c: (Int, Int) => Int => Int = $$Lambda$1085/947437179@7df28f1
```
