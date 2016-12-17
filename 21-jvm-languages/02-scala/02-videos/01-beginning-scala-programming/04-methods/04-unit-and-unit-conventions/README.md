# Unit And Unit Conventions

`Unit` means `void` in Java

- Unit doesn't give you anything
- Similar to Java's void
- Unit is actually an object
- Unit's full type name is scala.Unit
- Unit has one value: `()`
- Whenever you see () that is a Unit

*Unit*

`Unit` is subtype of `scala.AnyRef`

```
scala> ()

scala> val g = ()
g: Unit = ()

scala> val g:Unit = ()
g: Unit = ()

scala> val a = println("Scala Rocks")
Scala Rocks
a: Unit = ()

scala> def add(x:Int, y:Int) = {
     | if(x > 10) println(x)
     | else x + y
     | }
add: (x: Int, y: Int)AnyVal
```

http://scala-lang.org/api/current/#scala.Predef$
