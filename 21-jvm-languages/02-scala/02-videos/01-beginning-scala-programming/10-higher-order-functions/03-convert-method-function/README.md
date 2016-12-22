# Converting a method to a function

- Methods can be converted to functions using partially applied functions
- Use an underscore to turn method parameters into function parameters
- If an underscore is the last character in a method parameter, you can remove the underscore

**Convert method to function**

```
scala> class Foo(x:Int) {
     |   def bar(y:Int) = x + y
     | }
defined class Foo

scala> val x = new Foo(3)
x: Foo = Foo@46866946

scala> val f = x.bar _ <- convert method to function
f: Int => Int = $$Lambda$1049/1400671358@7308ffff
```
