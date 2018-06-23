# Vector vs List

https://alvinalexander.com/scala/make-vector-class-default-immutable-sequence-scala-cookbook
https://stackoverflow.com/questions/6928327/when-should-i-choose-vector-in-scala

Vector is faster than List

```
val l = List(1, 2, 3)
println(l)
// List(1, 2, 3)
val v = Vector(1, 2, 3)
println(v)
// Vector(1, 2, 3)
```
