# Functions with Functions

- Functions can take other functions in as parameter higher order functions
- Higher order function, the term, is used to also describe when a method can take a function
- Functions can also return other functions, useful for applying functions in parts

```
scala> val f:(Int, Int => Int) => Int = (x:Int, y:Int => Int) => y(x)
f: (Int, Int => Int) => Int = $$Lambda$1032/187649742@7e1f584d

scala> val f = (x:Int, y:Int => Int) => y(x)
f: (Int, Int => Int) => Int = $$Lambda$1043/1242874959@f08fdce
```
