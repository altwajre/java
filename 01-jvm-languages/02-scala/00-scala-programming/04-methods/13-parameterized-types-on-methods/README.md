# Parameterized Types On Methods

- Parameterized Types maintain type consistency
- Parameterized Types use [] not <>
- Parameterized Types are nearly equivalent to Java generics
- Parameterized Types are nearly equivalent to C++ templates
- Prefer use of single letters: A, B, C, D, E, T

*non generic type method*

```
scala> def decide(b:Boolean, x:Any, y:Any) = if (b) x else y
decide: (b: Boolean, x: Any, y: Any)Any

scala> decide(true, 'a', 'b')
res16: Any = a
```

*generic type method*

```
scala> def decide[T](b:Boolean, x:T, y:T) = if (b) x else y
decide: [T](b: Boolean, x: T, y: T)T

scala> decide(true, 'a', 'b')
res17: Char = a
```